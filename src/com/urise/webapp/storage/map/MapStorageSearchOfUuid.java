package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

import java.util.List;

public class MapStorageSearchOfUuid extends AbstractMapStorage<String> {

    @Override
    protected void updateResume(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected void saveResume(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(uuid);
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
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }
}
