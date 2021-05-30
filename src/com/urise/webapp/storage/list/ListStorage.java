package com.urise.webapp.storage.list;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(storage.get(index));
    }

    @Override
    public Collection<Resume> getAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void validToUpdate(Resume resume, int index) {
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    protected void validToSave(Resume resume, int index) {
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
