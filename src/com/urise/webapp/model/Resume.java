package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private Map<ContactType, String> contacts;
    private Map<SectionType, AbstractSection> sections;

    public Resume() {
        this.contacts = new EnumMap<>(ContactType.class);
        this.sections = new EnumMap<>(SectionType.class);
        this.addContactData(ContactType.PHONE_NUMBER, "");
        this.addContactData(ContactType.SKYPE, "");
        this.addContactData(ContactType.EMAIL, "");
        this.addContactData(ContactType.PROFILE_LINKEDIN, "");
        this.addContactData(ContactType.PROFILE_GITHUB, "");
        this.addContactData(ContactType.PROFILE_STACKOVERFLOW, "");
        this.addContactData(ContactType.HOME_PAGE, "");
        this.addSection(SectionType.PERSONAL, new TextSection(""));
        this.addSection(SectionType.PERSONAL, new TextSection(""));
        this.addSection(SectionType.ACHIEVEMENT, new ListSection(""));
        this.addSection(SectionType.QUALIFICATIONS, new ListSection(""));
        this.addSection(SectionType.EXPERIENCE, new OrganizationSection());
        this.addSection(SectionType.EDUCATION, new OrganizationSection());
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = new EnumMap<>(ContactType.class);
        this.sections = new EnumMap<>(SectionType.class);
        this.addContactData(ContactType.PHONE_NUMBER, "");
        this.addContactData(ContactType.SKYPE, "");
        this.addContactData(ContactType.EMAIL, "");
        this.addContactData(ContactType.PROFILE_LINKEDIN, "");
        this.addContactData(ContactType.PROFILE_GITHUB, "");
        this.addContactData(ContactType.PROFILE_STACKOVERFLOW, "");
        this.addContactData(ContactType.HOME_PAGE, "");
        this.addSection(SectionType.PERSONAL, new TextSection(""));
        this.addSection(SectionType.PERSONAL, new TextSection(""));
        this.addSection(SectionType.ACHIEVEMENT, new ListSection(""));
        this.addSection(SectionType.QUALIFICATIONS, new ListSection(""));
        this.addSection(SectionType.EXPERIENCE, new OrganizationSection());
        this.addSection(SectionType.EDUCATION, new OrganizationSection());
    }

    public void addContactData(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSection(SectionType sectionType, AbstractSection abstractSection) {
        sections.put(sectionType, abstractSection);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append("\n").append("\n");

        contacts.forEach((k, v) -> {
            builder.append(k.getTitle()).append(v).append("\n");
        });

        sections.forEach((k, v) -> {
            builder.append("\n").append(k.getTitle()).append("\n").append("\n").append(v.toString());
        });
        builder.append("\n");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contacts.equals(resume.contacts)) return false;
        return sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contacts.hashCode();
        result = 31 * result + sections.hashCode();
        return result;
    }
}