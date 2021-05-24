package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.StorageData.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_SAVED);
        Assert.assertEquals(RESUME_SAVED, storage.get(UUID_SAVED));
        Assert.assertEquals(STORAGE_SIZE_AFTER_SAVE, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void size() {
        Assert.assertEquals(STORAGE_SIZE_EXPECTED, storage.size());
    }

    @Test(expected = StorageException.class)
    public void overflow() {
        try {
            storage.clear();
            for (int i = 0; i < 10_000; i++) {
                Resume resume = new Resume(String.valueOf(i));
                storage.save(resume);
            }
        } catch (Exception e) {
            Assert.fail("That the overflow occurred ahead of time");
        }
        storage.save(RESUME_SAVED);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(EMPTY_STORAGE_SIZE, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(STORAGE_SIZE_AFTER_DELETE, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(EXPECTED_RESUMES, storage.getAll());
    }
}