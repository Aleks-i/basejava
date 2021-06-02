package com.urise.webapp.storage.array;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void saveResume(Resume resume) {
        checkForExistence(resume.getUuid());
        if (size == storage.length) {
            throw new StorageException("ERROR: resume storage overflowing", resume.getUuid());
        }
        insertResume(resume);
        size++;
    }

    @Override
    public Resume getResume() {
        return storage[index];
    }

    @Override
    public void deleteResume(String uuid) {
        System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertResume(Resume resume);
}
