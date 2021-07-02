package com.urise.webapp.model;

import java.net.URL;
import java.util.*;

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
        this.contactData = new HashMap<>();
        this.sectionData = new HashMap<>();
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

    public enum ContactType {
        PHONENUMBER("Тел.: "),
        SKYPE("Skype: "),
        EMAIL("Почта: "),
        PROFILE("Профиль соц. сетей: "),
        URL("Сайт: ");

        private final String title;

        ContactType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private interface Section {
    }

    public class TextSection implements Section {
        private final List<String> content;

        public TextSection(List<String> content) {
            this.content = content;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            content.forEach(c -> builder.append(c).append("\n"));
            builder.append("\n");
            return builder.toString();
        }
    }

    public class MarkerTextSection implements Section {
        private final List<TextSection> content;

        public MarkerTextSection(List<TextSection> content) {
            this.content = content;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            content.forEach(c -> builder.append(c.toString()));
            return builder.toString();
        }
    }

    public class UrlLinkSection implements Section {
        private final List<OrganizationSection> organizations;

        public UrlLinkSection() {
            this.organizations = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            organizations.forEach(o -> builder.append(o.toString()));
            return builder.toString();
        }

        public void addOrganizations(List<OrganizationSection> organizations) {
            this.organizations.addAll(organizations);
        }

        public class OrganizationSection {
            private final URL url;
            private final String timePeriod;
            private final String typeOfActivity;
            private TextSection content;

            public OrganizationSection(URL url, String timePeriod, String typeOfActivity, TextSection content) {
                this.url = url;
                this.timePeriod = timePeriod;
                this.typeOfActivity = typeOfActivity;
                this.content = content;
            }

            public OrganizationSection(URL url, String timePeriod, String typeOfActivity) {
                this.url = url;
                this.timePeriod = timePeriod;
                this.typeOfActivity = typeOfActivity;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append(url).append("\n").append("\n")
                        .append(String.format("%-20s", timePeriod))
                        .append(typeOfActivity).append("\n");
                if (content != null) {
                    content.content.forEach(c -> builder.append(String.format("%20s", ""))
                            .append(c).append("\n"));
                }
                builder.append("\n");
                return builder.toString();
            }
        }
    }
}
