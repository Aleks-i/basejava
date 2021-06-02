package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected int index;

    @Override
    public void update(Resume resume) {
        checkForAbsence(resume.getUuid());
        updateResume(resume);
    }

    @Override
    public void save(Resume resume) {
        checkForExistence(resume.getUuid());
        saveResume(resume);
    }

    @Override
    public Resume get(String uuid) {
        checkForAbsence(uuid);
        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        checkForAbsence(uuid);
        deleteResume(uuid);
    }

    public boolean isExist(int index) {
        return index >= 0;
    }

    protected void checkForExistence(String uuid) {
        index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected void checkForAbsence(String uuid) {
        index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void updateResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract int getIndex(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);
}
