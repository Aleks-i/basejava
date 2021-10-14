package com.urise.webapp.storage;

import com.urise.webapp.sql.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlStorageTest extends AbstractStorageTest {
    //  option 1
    private static final String URL = "jdbc:postgresql://localhost:5432/resumes";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public SqlStorageTest() {
        super(new SqlStorage(URL, USER, PASSWORD));
    }

    //  option 2
/*    private static String URL;
    private static String USER;
    private static String PASSWORD;
    protected static final File PROPS = new File("config\\resumes.properties");
    protected static Properties props = new Properties();

    public SqlStorageTest() {
        super(new SqlStorage(URL, USER, PASSWORD));
        try(InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }*/
}