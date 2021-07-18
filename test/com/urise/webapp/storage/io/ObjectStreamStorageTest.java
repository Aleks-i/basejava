package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.iostorage.ObjectStreamStorage;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGEDIR));
    }
}