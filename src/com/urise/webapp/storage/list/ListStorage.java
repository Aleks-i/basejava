package com.urise.webapp.storage.list;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    public Resume getResume(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public void deleteResume(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    public List<Resume> getAllSorted() {
        return getSortedResumeList(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}