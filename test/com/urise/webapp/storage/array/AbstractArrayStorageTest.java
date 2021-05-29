package com.urise.webapp.storage.array;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.StorageData.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(EXPECTED_RESUMES_ARRAY, storage.getAll());
        assertSize(STORAGE_SIZE_EXPECTED);
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
}
