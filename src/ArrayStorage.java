import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Resume resume;
        for (int i = 0; i < storage.length; i++) {
            resume = storage[i];
            if (resume != null) {
                storage[i] = null;
            } else {
                break;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                return resume;
            } else {
                break;
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume resume;
        for (int i = 0; i <= storage.length; i++) {
            resume = storage[i];
            if (resume != null && resume.uuid.equals(uuid)) {
                for (int j = i; j < storage.length; j++) {
                    if (storage[j] != null) {
                        storage[j] = storage[j + 1];
                        i++;
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, this.size());
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }
}
