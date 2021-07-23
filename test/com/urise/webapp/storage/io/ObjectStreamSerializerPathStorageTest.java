package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.ObjectStreamSerializer;

import java.nio.file.Paths;

public class ObjectStreamSerializerPathStorageTest extends AbstractStorageTest {

    public ObjectStreamSerializerPathStorageTest() {
        super(new PathStorage(new ObjectStreamSerializer(), Paths.get(STORAGEDIR)));
    }
}