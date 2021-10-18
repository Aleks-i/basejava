package com.urise.webapp.storage.sql;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.Storage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
        helper.<Void>execute("UPDATE resume SET full_name =? WHERE uuid =?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException("Резюме с id " + resume.getUuid() + " в базе даннх отсутствует");
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        helper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return helper.execute("SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
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
        return helper.execute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            List<Resume> storage = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                storage.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return storage;
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