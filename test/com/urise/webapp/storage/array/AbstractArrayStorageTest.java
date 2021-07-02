package com.urise.webapp.storage.array;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.ResumeTestData.RESUME;
import static com.urise.webapp.storage.ResumeTestData.RESUME_SAVED;
import static com.urise.webapp.storage.array.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void overflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                Resume resume = new Resume("uuid" + i);
                resume.setContactData(RESUME.getContactData());
                resume.setSectionData(RESUME.getSectionData());
                storage.save(resume);
            }
        } catch (StorageException e) {
            Assert.fail("That the overflow occurred ahead of time");
        }
        storage.save(RESUME_SAVED);
    }
}
