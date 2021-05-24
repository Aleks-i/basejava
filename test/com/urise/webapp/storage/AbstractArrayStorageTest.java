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
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_SAVED);
        Assert.assertEquals(RESUME_SAVED, storage.get(UUID_SAVED));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void overFlow() {
        try {
            storage.clear();
            for (int i = 0; i < 10_001; i++) {
                Resume resume = new Resume(String.valueOf(i));
                storage.save(resume);
            }
        } catch (StorageException e) {
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("That the overflow occurred ahead of time");
        }
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
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void update() {
        storage.save(RESUME_UPDATED);
        storage.update(RESUME_UPDATED);
        Assert.assertEquals(RESUME_UPDATED, storage.get(UUID_UPDATED));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Assert.assertEquals(RESUMES, storage.getAll());
    }
}