package com.urise.webapp.storage.io.serializers;

import com.urise.webapp.model.Resume;
import com.urise.webapp.util.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JSONParser.write(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JSONParser.read(reader, Resume.class);
        }
    }
}