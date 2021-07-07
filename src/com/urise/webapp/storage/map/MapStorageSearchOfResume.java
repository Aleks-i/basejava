package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

public class MapStorageSearchOfResume extends AbstractMapStorage<Resume> {

    @Override
    protected void updateResume(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void deleteResume(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.getOrDefault(uuid, null);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return storage.containsValue(searchKey);
    }
}