package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        if (!isPresent(resume.getUuid())) {
            System.out.println("ERROR: resume with uuid " + resume.getUuid() + " missing in the database");
        } else {
            storage[getIndexResumeInStorage(resume.getUuid())] = resume;
        }
    }

    public void save(Resume resume) {
        if (isPresent(resume.getUuid())) {
            System.out.println("ERROR: resume with uuid: " + resume.getUuid() + "  exist in the database");
        } else if (size >= 10_000) {
            System.out.println("ERROR: resume storage overflowing");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        Resume resume = null;
        if (!isPresent(uuid)) {
            System.out.println("ERROR: resume with uuid: " + uuid + "  missing in the database");
            return resume;
        }
        resume = storage[getIndexResumeInStorage(uuid)];
        return resume;
    }

    public void delete(String uuid) {
        if (!isPresent(uuid)) {
            System.out.println("ERROR: resume with uuid: " + uuid + "  missing in the database");
        } else {
            int i = getIndexResumeInStorage(uuid);
            while (i < size) {
                storage[i] = storage[i + 1];
                i++;
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public boolean isPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexResumeInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
