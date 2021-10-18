package com.urise.webapp.storage.sql;

import com.urise.webapp.storage.AbstractStorageTest;
import com.urise.webapp.util.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.sqlStorage);
    }
}