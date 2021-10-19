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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
                setParamPreparedStatement(ps, resume.getFullName(), uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException("Резюме с id " + resume.getUuid() + " в базе даннх отсутствует");
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact SET value =? WHERE resume_uuid =? AND type =?")) {
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    setParamPreparedStatement(ps, entry.getValue(), resume.getUuid(), entry.getKey().name());
                    ps.execute();
                    return null;
                }
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        helper.transactionExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                        setParamPreparedStatement(ps, resume.getUuid(), resume.getFullName());
                        ps.execute();
                    }
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
                        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                            setParamPreparedStatement(ps, resume.getUuid(), entry.getKey().name(), entry.getValue());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
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
        List<Resume> storage = new ArrayList<>();
        helper.execute("" +
                "SELECT * FROM resume ORDER BY full_name, uuid", ps -> {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Resume resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                storage.add(resume);
            }
            return storage;
        });
        storage.forEach(resume -> helper.execute("" +
                "SELECT * FROM contact WHERE resume_uuid =?", ps -> {
            ps.setString(1, resume.getUuid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resume.addContactData(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
            }
            return storage;
        }));
        return storage;
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

    private void setParamPreparedStatement(PreparedStatement ps, String... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            int parameterIndex = i + 1;
            ps.setString(parameterIndex, params[i]);
        }
    }
}