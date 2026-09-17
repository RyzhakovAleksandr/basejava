package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int SIZE_STORAGE = 10000;
    Resume[] storage = new Resume[SIZE_STORAGE];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = findResume(r.getUuid());
        if (index != -1) {
            System.out.printf("Невозможно сохранить %s. Резюме уже существует%n", r.getUuid());
            return;
        }
        if (size < storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("Хранилище переполнено");
        }
    }

    public void update(Resume r) {
        int index = findResume(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.printf("Невозможно обновить %s. Резюме не найдено%n", r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = findResume(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.printf("Невозможно вывести %s. Резюме не найдено%n", uuid);
        }

        return null;
    }

    public void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            if (index == size - 1) {
                storage[index] = null;
                size--;
            } else {
                for (int i = index; i <= size - 1; i++) {
                    storage[i] = storage[i + 1];
                }
                size--;
            }
        } else {
            System.out.printf("Невозможно удалить %s. Резюме не найдено%n", uuid);
        }
    }

    private int findResume(String uuid) {
        int result = -1;
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
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
    public Resume[] getAll() {
        Resume[] getStorage = new Resume[size];
        if (size > 0) System.arraycopy(storage, 0, getStorage, 0, size);
        return getStorage;
    }

    public int size() {
        return size;
    }
}
