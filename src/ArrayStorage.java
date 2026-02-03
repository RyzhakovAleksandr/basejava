/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        int index = findResume(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            if (index == size - 1) {
                size--;
            } else {
                for (int i = index; i <= size - 1; i++) {
                    storage[i] = storage[i + 1];
                }
                size--;
            }
        }
    }

    private int findResume(String uuid) {
        int result = -1;
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].uuid)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] getStorage = new Resume[size];
        if (size > 0) System.arraycopy(storage, 0, getStorage, 0, size);
        return getStorage;
    }

    int size() {
        return size;
    }
}
