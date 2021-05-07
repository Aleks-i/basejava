package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume with uuid: \"" + uuid + "\"  missing in the database");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume with uuid: \"" + uuid + "\"  missing in the database");
        } else {
            System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
            size--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR: resume with uuid \"" + resume.getUuid() + "\" missing in the database");
        } else {
            storage[index] = resume;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public abstract void save(Resume resume);

    protected abstract int getIndex(String uuid);
}
