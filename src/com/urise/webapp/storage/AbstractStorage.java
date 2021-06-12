package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {
    public static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public void update(Resume resume) {
        Object searchKey = searchKeyForAbsence(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKeyForExistence(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = searchKeyForAbsence(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = searchKeyForAbsence(uuid);
        deleteResume(searchKey);
    }

    protected Object getSearchKeyForExistence(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object searchKeyForAbsence(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public static List<Resume> getSortedResumeList(List<Resume> resumes) {
        return resumes.stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);
}
