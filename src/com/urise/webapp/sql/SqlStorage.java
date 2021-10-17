package com.urise.webapp.sql;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.Storage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.urise.webapp.storage.AbstractStorage.getSortedResumeList;

public class SqlStorage implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    SqlHelper helper;
    List<Resume> storage = new ArrayList<>();

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
        LOG.info("Update resume id: " + resume.getUuid());
        helper.execute("UPDATE resume SET full_name =? WHERE uuid =?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException("Резюме с id " + resume.getUuid() + " в базе даннх отсутствует");
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        helper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps -> {
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
        helper.execute("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            int affectedRecords = ps.executeUpdate();
            if (affectedRecords == 0) {
                throw new NotExistStorageException("Резюме с id " + uuid + " в базе даннх отсутствует");
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        return helper.execute("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                storage.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return getSortedResumeList(storage);
        });
    }

    @Override
    public int size() {
        LOG.info("Storage size");
        return helper.execute("SELECT COUNT(*) from resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException("Storage is empty");
            }
            return rs.getInt(1);
        });
    }
}