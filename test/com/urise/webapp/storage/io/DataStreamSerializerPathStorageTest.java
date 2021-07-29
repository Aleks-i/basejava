package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.DataStreamSerializer;

import java.nio.file.Paths;

public class DataStreamSerializerPathStorageTest extends AbstractStorageTest {

    public DataStreamSerializerPathStorageTest() {
        super(new PathStorage(new DataStreamSerializer(), Paths.get(STORAGEDIR)));
    }
}