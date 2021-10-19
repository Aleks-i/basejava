package com.urise.webapp.exception;

public class ExistStorageException extends StorageException{

    public ExistStorageException(String uuid) {
        super("ERROR: resume with uuid: \"" + uuid + "\"  exist in the database", uuid);
    }
}