package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

import java.util.List;

public class MapStorageSearchOfUuid extends AbstractMapStorage {

    @Override
    protected void updateResume(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected Resume getResume(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    protected void deleteResume(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return getSortedResumeList(List.copyOf(storage.values()));
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey((String) uuid);
    }
}
