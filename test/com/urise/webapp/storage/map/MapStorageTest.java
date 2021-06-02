package com.urise.webapp.storage.map;

import com.urise.webapp.storage.AbstractStorageTest;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.StorageData.*;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(EXPECTED_RESUMES_FOR_MAP, storage.getAll());
        assertSize(STORAGE_SIZE_EXPECTED);
    }
}