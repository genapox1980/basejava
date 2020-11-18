package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("Такое резюме уже есть");
        } else if (size >= storage.length) {
            System.out.println("Хранилище переполнено");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме отсутствует");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме отсутствует");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме отсутствует");
        } else {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
