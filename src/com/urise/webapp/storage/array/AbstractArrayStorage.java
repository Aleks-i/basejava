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
    protected void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        if (size == storage.length) {
            throw new StorageException("ERROR: resume storage overflowing", resume.getUuid());
        }
        insertResume(resume, (Integer) index);
        size++;
    }

    @Override
    public Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    public void deleteResume(Object index) {
        Integer idx = (Integer) index;
        System.arraycopy(storage, idx + 1, storage, idx, (size - idx - 1));
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
    public boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract void insertResume(Resume resume, int index);
}
