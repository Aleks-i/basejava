package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

public class MapStorageSearchOfResume extends AbstractMapStorage {

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.getOrDefault(uuid, null);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsValue((Resume) searchKey);
    }
}
