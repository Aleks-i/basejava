package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException{

    public NotExistStorageException(String uuid) {
        super("ERROR: resume with uuid: \"" + uuid + "\"  missing in the database", uuid);
    }
}
