package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.DataStreamSerializer;

import java.io.File;

public class DataStreamtreamSerializerFileStorageTest extends AbstractStorageTest {

    public DataStreamtreamSerializerFileStorageTest() {
        super(new FileStorage(new DataStreamSerializer(), new File(STORAGEDIR)));
    }
}