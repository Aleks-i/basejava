package com.urise.webapp.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, Set<String>> contactData;
    private Map<SectionType, AbstractSection> sectionData;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contactData = new HashMap<>();
        this.sectionData = new HashMap<>();
        sectionData = Map.of(
                SectionType.OBJECTIVE, new TextSection(),
                SectionType.PERSONAL, new TextSection(),
                SectionType.ACHIEVEMENT, new MarkerTextSection(),
                SectionType.QUALIFICATIONS, new MarkerTextSection(),
                SectionType.EXPERIENCE, new UrlLinkSection(),
                SectionType.EDUCATION, new UrlLinkSection()
        );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append("\n").append("\n");

        for (ContactType contactType : ContactType.values()) {
            contactData.get(contactType).forEach(c -> builder.append(contactType.getTitle()).append(c).append("\n"));
        }
        builder.append("\n");

        for (SectionType sectionType : SectionType.values()) {
            builder.append(sectionType.getTitle()).append("\n").append("\n").append(sectionData.get(sectionType).toString());
        }
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

    public void addContactData(ContactType contactType, String... contact) {
        contactData.merge(contactType, Set.of(contact), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public void addTextSection(SectionType sectionType, List<String> content) {
        sectionData.get(sectionType).getContent().add(new TextSection(content));
    }

    public void addMarkerSection(SectionType sectionType, Set<List<String>> content) {
        List<TextSection> textSections = content.stream()
                .map(TextSection::new)
                .collect(Collectors.toList());
        sectionData.get(sectionType).getContent().add(new MarkerTextSection(textSections));
    }

    public void addUrlLinkSection(SectionType sectionType, String url, String timePeriod,
                                  String typeOfActivity, List<String> content) {
        sectionData.get(sectionType).getContent().add(new OrganizationSection(url, timePeriod, typeOfActivity,
                new TextSection(content)
        ));
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

    public Map<SectionType, AbstractSection> getSectionData() {
        return sectionData;
    }

    public void setSectionData(Map<SectionType, AbstractSection> sectionData) {
        this.sectionData = sectionData;
    }
}
