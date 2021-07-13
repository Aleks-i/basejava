package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

import java.util.List;

public class MapStorageSearchOfUuid extends AbstractMapStorage<String> {

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        storage.put(uuid, resume);
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
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