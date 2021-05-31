package com.urise.webapp.storage.array;

import com.urise.webapp.exception.ExistStorageException;
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
    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    public Resume getResume(int index) {
        return storage[index];
    }

    @Override
    public void deleteResume(String uuid, int index) {
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

    @Override
    protected void checkingForExistence(boolean exist, String uuid) {
        if (exist) {
            throw new ExistStorageException(uuid);
        } else if (size == storage.length) {
            throw new StorageException("ERROR: resume storage overflowing", uuid);
        }
    }

    protected abstract void saveResume(Resume resume, int index);
}
