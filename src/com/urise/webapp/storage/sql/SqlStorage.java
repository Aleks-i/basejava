package com.urise.webapp.storage.sql;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.urise.webapp.storage.AbstractStorage.getSortedResumeList;

public class SqlStorage implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
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
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact WHERE resume_uuid =? SET type =? AND value =?")) {
                addContacts(resume, ps);
            }
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
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
                        addContacts(resume, ps);
                    }
                    return null;
                }
        );
    }

    private void addContacts(Resume resume, PreparedStatement ps) throws SQLException {
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, entry.getKey().name());
            ps.setString(3, entry.getValue());
            ps.addBatch();
        }
        ps.executeBatch();
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return helper.execute("" +
                " SELECT * FROM resume r " +
                "   LEFT JOIN contact c " +
                "       ON r.uuid = c.resume_uuid " +
                " WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));

            do {
                String value = rs.getString("value");
                if (value == null) {
                    continue;
                }
                ContactType contactType = ContactType.valueOf(rs.getString("type"));
                resume.addContactData(contactType, value);
            } while (rs.next());
            return resume;
        });
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
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        return getSortedResumeList(new ArrayList<>(helper.execute("" +
                "SELECT * FROM resume " +
                "   LEFT JOIN contact c on resume.uuid = c.resume_uuid" +
                "       ORDER BY full_name, uuid", ps -> {
            Map<String, Resume> storage = new HashMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                storage.putIfAbsent(uuid, new Resume(uuid, rs.getString("full_name")));
                storage.get(uuid).addContactData(ContactType.valueOf(rs.getString("type")),
                        rs.getString("value"));
            }
            return storage;
        }).values()));
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