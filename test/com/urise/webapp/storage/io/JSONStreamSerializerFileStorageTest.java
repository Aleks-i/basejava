package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.JSONStreamSerializer;

import java.io.File;

public class JSONStreamSerializerFileStorageTest extends AbstractStorageTest {

    public JSONStreamSerializerFileStorageTest() {
        super(new FileStorage(new JSONStreamSerializer(), new File(STORAGEDIR)));
    }
}