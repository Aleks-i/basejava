package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.urise.webapp.storage.ResumeTestData.*;

public abstract class AbstractStorageTest {
    protected final static String STORAGEDIR = ".\\storage";

    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(EMPTY_STORAGE_SIZE);
    }

    @Test
    public void update() {
        storage.save(RESUME_BEFORE_UPDATE);
        storage.update(RESUME_UPDATED);
        assertGet(RESUME_UPDATED);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void save() {
        storage.save(RESUME_SAVED);
        assertGet(RESUME_SAVED);
        assertSize(STORAGE_SIZE_AFTER_SAVE);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_2.getUuid());
        assertSize(STORAGE_SIZE_AFTER_DELETE);
        storage.get(RESUME_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        Assert.assertEquals(EXPECTED_RESUMES, storage.getAllSorted());
        assertSize(STORAGE_SIZE_EXPECTED);
    }

    @Test
    public void size() {
        assertSize(STORAGE_SIZE_EXPECTED);
    }

    protected void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}