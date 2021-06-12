package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getAllSorted() {
        return getSortedResumeList(List.copyOf(storage.values()));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
