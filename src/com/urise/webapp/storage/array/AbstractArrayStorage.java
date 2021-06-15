package com.urise.webapp.storage.array;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Integer index) {
        if (size == storage.length) {
            throw new StorageException("ERROR: resume storage overflowing", resume.getUuid());
        }
        insertResume(resume, index);
        size++;
    }

    @Override
    public Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    public void deleteResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
        size--;
    }

    @Override
    public List<Resume> getAllSorted() {
        return getSortedResumeList(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void insertResume(Resume resume, int index);
}
