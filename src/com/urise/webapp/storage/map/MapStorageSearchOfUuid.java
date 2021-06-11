package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;

import java.util.List;

import static com.urise.webapp.util.Util.getSortedResumeList;

public class MapStorageSearchOfUuid extends AbstractMapStorage {

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        return getSortedResumeList(List.copyOf(storage.values()));
    }

    @Override
    protected String getSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}
