package com.urise.webapp.storage.sql;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        this.helper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        LOG.info("Clear");
        helper.execute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        LOG.info("Update resume id: " + uuid);
        helper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid =?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException("Резюме с id " + resume.getUuid() + " в базе даннх отсутствует");
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM text_list_section WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ps.executeUpdate();
            }
            addContacts(resume, conn);
            addTextListSections(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        helper.transactionExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    addContacts(resume, conn);
                    addTextListSections(resume, conn);
                    return null;
                }
        );
    }

    private void addContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (type_contact, value_contact, resume_uuid) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addTextListSections(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO text_list_section (type_text, value_text, resume_uuid) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                StringBuilder contenet = new StringBuilder();
                if (entry.getKey().equals(SectionType.ACHIEVEMENT) || entry.getKey().equals(SectionType.QUALIFICATIONS)) {
                    ((ListSection) entry.getValue()).getItems().forEach(i -> contenet.append(i).append("\n"));
                } else {
                    contenet.append(((TextSection) entry.getValue()).getContent());
                }
                ps.setString(1, entry.getKey().name());
                ps.setString(2, contenet.toString().trim());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        String query = " SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid " +
                "                                       LEFT JOIN text_list_section t ON r.uuid = t.resume_uuid " +
                "                                       WHERE r.uuid =?";
        return helper.execute(query, ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = null;
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                if (resume == null && fullName != null) {
                    resume = new Resume(uuid, fullName);
                }
                String contact = rs.getString("value_contact");
                if (contact != null) {
                    resume.addContactData(ContactType.valueOf(rs.getString("type_contact")),
                            rs.getString("value_contact"));
                }
                String textListSection = rs.getString("value_text");
                if (textListSection != null) {
                    SectionType sectionType = SectionType.valueOf(rs.getString("type_text"));
                    resume.addSection(sectionType,
                            getSection(sectionType, rs.getString("value_text")));
                }
            }
            return resume;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        Map<String, Resume> storage = new LinkedHashMap<>();
        String queryResumes = "SELECT * FROM resume LEFT JOIN text_list_section t ON resume.uuid = t.resume_uuid " +
                "                                       ORDER BY full_name, uuid";
        helper.execute(queryResumes, ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = new Resume(uuid, rs.getString("full_name"));
                storage.putIfAbsent(uuid, resume);
                String textListSection = rs.getString("value_text");
                if (textListSection != null) {
                    SectionType sectionType = SectionType.valueOf(rs.getString("type_text"));
                    storage.get(uuid).addSection(sectionType,
                            getSection(sectionType, rs.getString("value_text")));
                }
            }
            return storage;
        });

        String queryContacts = "SELECT * FROM contact c";
        helper.execute(queryContacts, ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String resumeUuid = rs.getString("resume_uuid");
                String typeContact = rs.getString("type_contact");
                String valueContact = rs.getString("value_contact");
                storage.get(resumeUuid).addContactData(ContactType.valueOf(typeContact), valueContact);
            }
            return null;
        });
        return new ArrayList<>(storage.values());
    }

    private AbstractSection getSection(SectionType sectionType, String content) {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                return new TextSection(content);
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(content.split("\n"));
        }
        throw new StorageException("not exist section type");
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        helper.<Void>execute("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException("Резюме с id " + uuid + " в базе даннх отсутствует");
            }
            return null;
        });
    }

    @Override
    public int size() {
        LOG.info("Storage size");
        return helper.execute("SELECT COUNT(*) from resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }
}