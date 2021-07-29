package com.urise.webapp.storage.io.serializers;

import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.OrganizationSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;
import com.urise.webapp.model.organization.Link;
import com.urise.webapp.model.organization.Organization;
import com.urise.webapp.util.xml.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer {
    private final Parser parser;

    public XmlStreamSerializer() {
        parser = new Parser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, ListSection.class, Organization.Position.class
        );
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            parser.marshall(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return parser.unmarshall(reader);
        }
    }
}