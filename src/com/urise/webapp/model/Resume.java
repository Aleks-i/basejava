package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.urise.webapp.model.SectionType.*;

/**
 * Initial resume class
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, Set<String>> contactData;
    private Map<SectionType, Section> sectionData;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contactData = new EnumMap<>(ContactType.class);
        this.sectionData = new EnumMap<>(SectionType.class);
        sectionData.put(OBJECTIVE, new TextSection());
        sectionData.put(PERSONAL, new TextSection());
        sectionData.put(ACHIEVEMENT, new BulletedListSection());
        sectionData.put(QUALIFICATIONS, new BulletedListSection());
        sectionData.put(EXPERIENCE, new Organization());
        sectionData.put(EDUCATION, new Organization());
    }

    public void addContactData(ContactType contactType, String... contact) {
        contactData.merge(contactType, Set.of(contact), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public void addTextSection(SectionType sectionType, String content) {
        sectionData.put(sectionType, new TextSection(content));
    }

    public void addBulletedListSection(SectionType sectionType, Set<List<String>> content) {
        BulletedListSection bulletedListSection = new BulletedListSection();
        content.forEach(c -> c.forEach(s -> bulletedListSection.getContent().add(new TextSection(s))));
        sectionData.put(sectionType, bulletedListSection);
    }

    public void addOrganizationSection(SectionType sectionType, String url, LocalDate startDate, LocalDate endDate,
                                       String typeOfActivity, List<String> content) {
        sectionData.putIfAbsent(sectionType, new Organization());
        List<TextSection> textSections = new ArrayList<>();
        if (content != null) {
            textSections = content.stream().map(TextSection::new).collect(Collectors.toList());
        }
        Position position = new Position(url, startDate, endDate, typeOfActivity,
                new BulletedListSection(textSections));
        ((Organization) sectionData.get(sectionType)).getContent().add(position);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, Set<String>> getContactData() {
        return contactData;
    }

    public void setContactData(Map<ContactType, Set<String>> contactData) {
        this.contactData = contactData;
    }

    public Map<SectionType, Section> getSectionData() {
        return sectionData;
    }

    public void setSectionData(Map<SectionType, Section> sectionData) {
        this.sectionData = sectionData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append("\n").append("\n");

        contactData.forEach((k, v) -> {
            v.forEach(contact -> builder.append(k.getTitle()).append(contact).append("\n"));
        });

        sectionData.forEach((k, v) -> {
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
        if (!contactData.equals(resume.contactData)) return false;
        return sectionData.equals(resume.sectionData);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contactData.hashCode();
        result = 31 * result + sectionData.hashCode();
        return result;
    }
}