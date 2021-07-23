package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.ObjectStreamSerializer;

import java.io.File;

public class ObjectStreamSerializerFileStorageTest extends AbstractStorageTest {

    public ObjectStreamSerializerFileStorageTest() {
        super(new FileStorage(new ObjectStreamSerializer(), new File(STORAGEDIR)));
    }
}