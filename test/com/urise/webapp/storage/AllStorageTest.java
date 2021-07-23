package com.urise.webapp.storage;

import com.urise.webapp.storage.array.ArrayStorageTest;
import com.urise.webapp.storage.array.SortedArrayStorageTest;
import com.urise.webapp.storage.io.ObjectStreamSerializerFileStorageTest;
import com.urise.webapp.storage.io.ObjectStreamSerializerPathStorageTest;
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
        ObjectStreamSerializerFileStorageTest.class
})
public class AllStorageTest {
}