package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = checkForAbsence(resume.getUuid());
        updateResume(resume, index);
    }

    @Override
    public void save(Resume resume) {
        int index = checkForExistence(resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = checkForAbsence(uuid);
        return getResume(uuid, index);
    }

    @Override
    public void delete(String uuid) {
        int index = checkForAbsence(uuid);
        deleteResume(uuid, index);
    }

    public boolean isExist(int index) {
        return index >= 0;
    }

    protected int checkForExistence(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected int checkForAbsence(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void deleteResume(String uuid, int index);
}
