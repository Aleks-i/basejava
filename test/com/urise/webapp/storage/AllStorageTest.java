package com.urise.webapp.storage;

import com.urise.webapp.storage.array.ArrayStorageTest;
import com.urise.webapp.storage.array.SortedArrayStorageTest;
import com.urise.webapp.storage.io.*;
import com.urise.webapp.storage.list.ListStorageTest;
import com.urise.webapp.storage.map.MapStorageSearchOfResumeTest;
import com.urise.webapp.storage.map.MapStorageSearchOfUuidTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageSearchOfUuidTest.class,
        MapStorageSearchOfResumeTest.class,
        ObjectStreamSerializerPathStorageTest.class,
        ObjectStreamSerializerFileStorageTest.class,
        XMLStreamSerializerPathStorageTest.class,
        XMLStreamSerializerFileStorageTest.class,
        JSONStreamSerializerFileStorageTest.class,
        JSONStreamSerializerPathStorageTest.class,
        DataStreamtreamSerializerFileStorageTest.class,
        DataStreamSerializerPathStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}