package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class StorageData {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_SAVED = "uuidsaved";
    public static final String UUID_NOT_EXIST = "dummy";
    public static final int EMPTY_STORAGE_SIZE = 0;
    public static final int STORAGE_SIZE_EXPECTED = 3;
    public static final int STORAGE_SIZE_AFTER_DELETE = 2;
    public static final int STORAGE_SIZE_AFTER_SAVE = 4;

    public static final Resume RESUME_1 = new Resume(UUID_1);
    public static final Resume RESUME_2 = new Resume(UUID_2);
    public static final Resume RESUME_3 = new Resume(UUID_3);
    public static final Resume RESUME_SAVED = new Resume(UUID_SAVED);
    public static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);

    public static final Resume[] EXPECTED_RESUMES = new Resume[] {RESUME_1, RESUME_2, RESUME_3};
    public static final Resume[] EXPECTED_RESUMES_FOR_MAP = new Resume[] {RESUME_2, RESUME_1, RESUME_3};
}
