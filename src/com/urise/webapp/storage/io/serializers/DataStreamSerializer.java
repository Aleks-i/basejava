package com.urise.webapp.storage.io.serializers;

import com.urise.webapp.model.*;
import com.urise.webapp.model.organization.Link;
import com.urise.webapp.model.organization.Organization;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.urise.webapp.util.DateUtil.toStringLocalDate;

public class DataStreamSerializer implements StreamSerializer {

    private void writeStringList(List<String> list, DataOutputStream dos) throws IOException {
        int sizeListsection = list.size();
        dos.writeInt(sizeListsection);
        list.forEach(l -> {
            try {
                dos.writeUTF(l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void writeOrganizationList(List<Organization> list, DataOutputStream dos) throws IOException {
        int sizeOrganizationList = list.size();
        dos.writeInt(sizeOrganizationList);
        list.forEach(organization -> {
            try {
                dos.writeUTF(organization.getHomePage().getName());
                dos.writeUTF(organization.getHomePage().getUrl());
                List<Organization.Position> positionList = organization.getPositions();
                dos.writeInt(positionList.size());
                positionList.forEach(position -> {
                    try {
                        dos.writeUTF(toStringLocalDate(position.getStartDate()));
                        dos.writeUTF(toStringLocalDate(position.getEndDate()));
                        dos.writeUTF(position.getTitle());
                        String decription = position.getDescription();
                        dos.writeUTF(decription == null ? "null" : decription);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> readStringSection(DataInputStream dis) throws IOException {
        dis.readUTF();
        int listSize = dis.readInt();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            stringList.add(dis.readUTF());
        }
        return stringList;
    }

    private List<Organization> readOrganizationSection(DataInputStream dis) throws IOException {
        dis.readUTF();
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
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(entry.getKey().getTitle());
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        dos.writeUTF(entry.getKey().getTitle());
                        writeStringList(((ListSection) entry.getValue()).getItems(), dos);
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        dos.writeUTF(entry.getKey().getTitle());
                        writeOrganizationList(((OrganizationSection) entry.getValue()).getOrganizations(), dos);
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContactData(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            dis.readUTF();
            AbstractSection objectiveSection = new TextSection(dis.readUTF());
            resume.addSection(SectionType.OBJECTIVE, objectiveSection);
            dis.readUTF();
            AbstractSection personalSection = new TextSection(dis.readUTF());
            resume.addSection(SectionType.PERSONAL, personalSection);

            AbstractSection achievementSection = new ListSection(readStringSection(dis));
            resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
            AbstractSection qualificationtSection = new ListSection(readStringSection(dis));
            resume.addSection(SectionType.QUALIFICATIONS, qualificationtSection);

            AbstractSection experienceSection = new OrganizationSection(readOrganizationSection(dis));
            resume.addSection(SectionType.EXPERIENCE, experienceSection);
            AbstractSection educationSection = new OrganizationSection(readOrganizationSection(dis));
            resume.addSection(SectionType.EDUCATION, educationSection);
            return resume;
        }
    }
}