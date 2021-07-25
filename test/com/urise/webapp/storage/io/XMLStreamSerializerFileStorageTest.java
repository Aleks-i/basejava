package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.XmlStreamSerializer;

import java.io.File;

public class XMLStreamSerializerFileStorageTest extends AbstractStorageTest {

    public XMLStreamSerializerFileStorageTest() {
        super(new FileStorage(new XmlStreamSerializer(), new File(STORAGEDIR)));
    }
}