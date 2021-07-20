package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.iostorage.FileStorage;
import com.urise.webapp.storage.iostorage.serializers.ObjectStream;

import java.io.File;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(new ObjectStream(), new File(STORAGEDIR)));
    }
}