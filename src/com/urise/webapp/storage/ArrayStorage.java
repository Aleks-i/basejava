package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR: resume with uuid: " + resume.getUuid() + "  exist in the database");
        } else if (size == storage.length) {
            System.out.println("ERROR: resume storage overflowing");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
