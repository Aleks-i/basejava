package com.urise.webapp.storage.io.serializers;

import com.urise.webapp.model.*;
import com.urise.webapp.model.organization.Link;
import com.urise.webapp.model.organization.Organization;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.util.DateUtil.toStringLocalDate;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case OBJECTIVE, PERSONAL -> {
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        writeCollectionWithException(((ListSection) entry.getValue()).getItems(), dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        writeCollectionWithException(((OrganizationSection) entry.getValue()).getOrganizations(), dos, organization -> {
                            Link homePage = organization.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            writeCollectionWithException(organization.getPositions(), dos, position -> {
                                dos.writeUTF(toStringLocalDate(position.getStartDate()));
                                dos.writeUTF(toStringLocalDate(position.getEndDate()));
                                dos.writeUTF(position.getTitle());
                                String decription = position.getDescription();
                                dos.writeUTF(decription == null ? "null" : decription);
                            });
                        });
                    }
                }
            }
        }
    }

    private <T> void writeCollectionWithException(Collection<T> collection, DataOutputStream dos, SectionWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T tCollection : collection) {
            writer.write(tCollection);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            readData(dis, () -> resume.setContactData(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readData(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.setSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.setSection(sectionType, new ListSection(readCollectionWithException(dis,
                            dis::readUTF)));
                    case EXPERIENCE, EDUCATION -> resume.setSection(sectionType, new OrganizationSection(readCollectionWithException(dis,
                            () -> new Organization(new Link(dis.readUTF(), dis.readUTF()), readCollectionWithException(dis,
                                    () -> new Organization.Position(LocalDate.parse(dis.readUTF()),
                                            LocalDate.parse(dis.readUTF()),
                                            dis.readUTF(),
                                            getDescription(dis.readUTF())
                                    )))
                    )));
                }
            });
            return resume;
        }
    }

    private void readData(DataInputStream dis, DataReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private <T> List<T> readCollectionWithException(DataInputStream dis, SectionReader<T> reader) throws IOException {
        int listSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private String getDescription(String description) {
        return description.equals("null") ? null : description;
    }

    @FunctionalInterface
    public interface SectionWriter<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    public interface DataReader {
        void read() throws IOException;
    }

    @FunctionalInterface
    public interface SectionReader<T> {
        T read() throws IOException;
    }
}