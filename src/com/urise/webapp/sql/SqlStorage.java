package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;
import com.urise.webapp.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.urise.webapp.storage.AbstractStorage.getSortedResumeList;

public class SqlStorage implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    public final ConnectionFactory connectionFactory;
    List<Resume> storage = new ArrayList<>();

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        LOG.info("Clear");
        execute(new BlockCode() {
            @Override
            public <T> T execute(PreparedStatement ps) {
                try {
                    ps.execute();
                } catch (SQLException e) {
                    throw new NotExistStorageException(e);
                }
                return null;
            }
        }, "DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update resume id: " + resume.getUuid());
        execute(new BlockCode() {
            @Override
            public <T> T execute(PreparedStatement ps) {
                String uuid = resume.getUuid();
                try {
                    ps.setString(1, uuid);
                    ps.setString(2, resume.getFullName());
                    ps.setString(3, uuid);
                    int affectedRecords = ps.executeUpdate();
                    if (affectedRecords == 0) {
                        throw new NotExistStorageException("Резюме с id " + resume.getUuid() + " в базе даннх отсутствует");
                    }
                } catch (SQLException e) {
                    throw new NotExistStorageException(e);
                }
                return null;
            }
        }, "UPDATE resume SET uuid =?, full_name =? WHERE uuid =?");
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        execute(new BlockCode() {
            @Override
            public <T> T execute(PreparedStatement ps) {
                try {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                } catch (SQLException e) {
                    throw new ExistStorageException(e);
                }
                return null;
            }
        }, "INSERT INTO resume (uuid, full_name) VALUES (?, ?)");
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return execute(new BlockCode() {
            @Override
            public Resume execute(PreparedStatement ps) {
                try {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(uuid, rs.getString("full_name"));
                } catch (SQLException e) {
                    throw new ExistStorageException(e);
                }
            }
        }, "SELECT * FROM resume r WHERE r.uuid =?");
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        execute(new BlockCode() {
            @Override
            public <T> T execute(PreparedStatement ps) {
                try {
                    ps.setString(1, uuid);
                    int affectedRecords = ps.executeUpdate();
                    if (affectedRecords == 0) {
                        throw new NotExistStorageException("Резюме с id " + uuid + " в базе даннх отсутствует");
                    }
                } catch (SQLException e) {
                    throw new ExistStorageException(e);
                }
                return null;
            }
        }, "DELETE FROM resume WHERE uuid =?");
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        return execute(new BlockCode() {
            @Override
            public List<Resume> execute(PreparedStatement ps) {
                try {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        storage.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
                    }
                    return getSortedResumeList(storage);
                } catch (SQLException e) {
                    throw new ExistStorageException(e);
                }
            }
        }, "SELECT * FROM resume");
    }

    @Override
    public int size() {
        LOG.info("Storage size");
        return execute(new BlockCode() {
            @Override
            public Integer execute(PreparedStatement ps) {
                try {
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException("Storage is empty");
                    }
                    return rs.getInt(1);
                } catch (SQLException e) {
                    throw new ExistStorageException(e);
                }
            }
        }, "SELECT COUNT(*) from resume");
    }

    private <T> T execute(BlockCode blockCode, String query) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            return blockCode.execute(preparedStatement);
        } catch (SQLException e) {
            throw new ExistStorageException(e);
        }
    }
}