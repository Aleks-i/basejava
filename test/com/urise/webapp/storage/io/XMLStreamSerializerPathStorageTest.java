package com.urise.webapp.storage.io;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.storage.io.serializers.XmlStreamSerializer;

import java.nio.file.Paths;

public class XMLStreamSerializerPathStorageTest extends AbstractStorageTest {

    public XMLStreamSerializerPathStorageTest() {
        super(new PathStorage(new XmlStreamSerializer(), Paths.get(STORAGEDIR)));
    }
}