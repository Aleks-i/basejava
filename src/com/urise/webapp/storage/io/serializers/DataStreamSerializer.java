package com.urise.webapp.storage.io.serializers;

import com.urise.webapp.model.*;
import com.urise.webapp.model.organization.Link;
import com.urise.webapp.model.organization.Organization;
import com.urise.webapp.util.Writer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

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

            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                switch (entry.getKey().name()) {
                    case "OBJECTIVE", "PERSONAL" -> {
                        dos.writeUTF(entry.getKey().getTitle());
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                    }
                    case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                        dos.writeUTF(entry.getKey().getTitle());
                        writeWithException(((ListSection) entry.getValue()).getItems(), dos, dos :: writeUTF);
                    }
                    case "EXPERIENCE", "EDUCATION" -> {
                        dos.writeUTF(entry.getKey().getTitle());
                        writeWithException(((OrganizationSection) entry.getValue()).getOrganizations(), dos, organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            writeWithException(organization.getPositions(), dos, position -> {
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

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContactData(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            while (true) {
                SectionType sectionType = getSectionType(dis);
                if (sectionType == null) {
                    break;
                }
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.addSection(sectionType, new ListSection(getStringsForListSection(dis)));
                    case EXPERIENCE, EDUCATION -> resume.addSection(sectionType, new OrganizationSection(getOrganizationsForOrganizationSection(dis)));
                }
            }
            return resume;
        }
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T tCollection : collection) {
            writer.write(tCollection);
        }
    }

    private SectionType getSectionType(DataInputStream dis) throws IOException {
        try {
            String sectionTitle = dis.readUTF();
            return Arrays.stream(SectionType.values())
                    .filter(s -> s.getTitle().equals(sectionTitle))
                    .findFirst().orElse(null);
        } catch (EOFException e) {
            return null;
        }
    }

    private List<String> getStringsForListSection(DataInputStream dis) throws IOException {
        int listSize = dis.readInt();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            stringList.add(dis.readUTF());
        }
        return stringList;
    }

    private List<Organization> getOrganizationsForOrganizationSection(DataInputStream dis) throws IOException {
        int listOrganizationSize = dis.readInt();
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < listOrganizationSize; i++) {
            Link link = new Link(dis.readUTF(), dis.readUTF());
            int listPositionSize = dis.readInt();
            List<Organization.Position> positions = new ArrayList<>();
            for (int j = 0; j < listPositionSize; j++) {
                LocalDate dateStrat = LocalDate.parse(dis.readUTF());
                LocalDate dateEnd = LocalDate.parse(dis.readUTF());
                String title = dis.readUTF();
                String description = dis.readUTF();
                positions.add(new Organization.Position(dateStrat, dateEnd, title, description.equals("null") ? null : description));
            }
            organizations.add(new Organization(link, positions));
        }
        return organizations;
    }
}