package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.JSONStreamSerializer;

import java.nio.file.Paths;

public class JSONStreamSerializerPathStorageTest extends AbstractStorageTest {

    public JSONStreamSerializerPathStorageTest() {
        super(new PathStorage(new JSONStreamSerializer(), Paths.get(STORAGEDIR)));
    }
}