package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class StorageData {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_SAVED = "uuidsaved";
    public static final String UUID_UPDATED = "uuidupdated";
    public static final String UUID_NOT_EXIST = "dummy";

    public static final Resume RESUME_1 = new Resume(UUID_1);
    public static final Resume RESUME_2 = new Resume(UUID_2);
    public static final Resume RESUME_3 = new Resume(UUID_3);
    public static final Resume RESUME_SAVED = new Resume(UUID_SAVED);
    public static final Resume RESUME_UPDATED = new Resume(UUID_UPDATED);
    public static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);

    public static final Resume[] RESUMES = new Resume[] {RESUME_1, RESUME_2, RESUME_3};
}
