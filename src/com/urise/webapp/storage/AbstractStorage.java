package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected final Resume[] storage = new Resume[10_000];

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        checkingForAbsence(isExist(index), resume.getUuid());
        updateResume(resume, index);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        checkingForExistence(isExist(index), resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        checkingForAbsence(isExist(index), uuid);
        return storage[index];
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        checkingForAbsence(isExist(index), uuid);
        deleteResume(uuid, index);
    }

    public boolean isExist(int index) {
        return index >= 0;
    }

    protected void checkingForExistence(boolean exist, String uuid) {
        if (exist) {
            throw new ExistStorageException(uuid);
        }
    }

    protected void checkingForAbsence(boolean exist, String uuid) {
        if (!exist) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);

    protected abstract void deleteResume(String uuid, int index);
}
