package com.urise.webapp.storage.list;

import com.urise.webapp.storage.AbstractStorageTest;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.StorageData.*;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(EXPECTED_RESUMES_LIST, storage.getAll());
        assertSize(STORAGE_SIZE_EXPECTED);
    }
}