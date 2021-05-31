package com.urise.webapp.storage.map;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractStorageTest;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.StorageData.*;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    @Override
    public void getAll() {
        Resume[] actualResume = storage.getAll();
        Assert.assertEquals(RESUME_2, actualResume[0]);
        Assert.assertEquals(RESUME_1, actualResume[1]);
        Assert.assertEquals(RESUME_3, actualResume[2]);
        assertSize(STORAGE_SIZE_EXPECTED);
    }
}