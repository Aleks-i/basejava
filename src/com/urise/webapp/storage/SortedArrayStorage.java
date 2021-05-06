package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = Math.abs(getIndex(resume.getUuid())) - 1;
        if (resume.equals(storage[index])) {
            System.out.println("ERROR: resume with uuid: " + resume.getUuid() + "  exist in the database");
        } else if (size == storage.length) {
            System.out.println("ERROR: resume storage overflowing");
        } else {
            System.arraycopy(storage, index, storage, index + 1, (size - index));
            storage[index] = resume;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
