package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.iostorage.serializers.ObjectStream;
import com.urise.webapp.storage.iostorage.PathStorage;

import java.nio.file.Paths;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(new ObjectStream(), Paths.get(STORAGEDIR)));
    }
}