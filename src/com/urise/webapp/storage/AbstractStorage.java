package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        validToUpdate(resume, index);
        updateResume(resume, index);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        validToSave(resume, index);
        saveResume(resume, index);
    }

    protected abstract void validToUpdate(Resume resume, int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void validToSave(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
