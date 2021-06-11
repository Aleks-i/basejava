package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public class StorageData {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";
    public static final String UUID_SAVED = "uuidsaved";
    public static final String UUID_NOT_EXIST = "dummy";

    public static final String FULL_NAME_1 = "Petrov";
    public static final String FULL_NAME_2 = "Sidorov";
    public static final String FULL_NAME_3 = "Ivanov";
    public static final String FULL_NAME_4 = "Petrov";
    public static final String FULL_NAME_SAVED = "Name Saved";
    public static final String FULL_NAME_UPDATED = "Name Updated";
    public static final String FULL_NAME_NOT_EXIST = "";

    public static final int EMPTY_STORAGE_SIZE = 0;
    public static final int STORAGE_SIZE_EXPECTED = 4;
    public static final int STORAGE_SIZE_AFTER_DELETE = 3;
    public static final int STORAGE_SIZE_AFTER_SAVE = 5;

    public static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    public static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    public static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
    public static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    public static final Resume RESUME_SAVED = new Resume(UUID_SAVED, FULL_NAME_SAVED);
    public static final Resume RESUME_UPDATED = new Resume(UUID_1, FULL_NAME_UPDATED);
    public static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST, FULL_NAME_NOT_EXIST);

    public static final List<Resume> EXPECTED_RESUMES = List.of(RESUME_3, RESUME_1, RESUME_4, RESUME_2);
}
