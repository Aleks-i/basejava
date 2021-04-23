package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        if (!isPresent(r)) {
            System.out.println("ERROR resume missing in the database");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                    break;
                }
            }
        }
    }

    public void save(Resume r) {
        if (isPresent(r)) {
            System.out.println("ERROR resume exist in the database");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    public void delete(String uuid) {
        if (!isPresent(get(uuid))) {
            System.out.println("ERROR resume missing in the database");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    while (i < size) {
                        storage[i] = storage[i + 1];
                        i++;
                    }
                    size--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public boolean isPresent(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                return true;
            }
        }
        return false;
    }
}
