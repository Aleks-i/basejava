package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    @Override
    public void update(Resume resume) {
        LOG.info("Update resume id: " + resume.getUuid());
        SK searchKey = searchKeyForAbsence(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume id " + resume.getUuid());
        SK searchKey = getSearchKeyForExistence(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = searchKeyForAbsence(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = searchKeyForAbsence(uuid);
        deleteResume(searchKey);
    }

    protected SK getSearchKeyForExistence(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("ERROR: resume with uuid: \"" + uuid + "\"  exist in the database");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SK searchKeyForAbsence(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("ERROR: resume with uuid: \"" + uuid + "\"  missing in the database");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public static List<Resume> getSortedResumeList(List<Resume> resumes) {
        LOG.info("GetAllSorted");
        return resumes.stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }
}
