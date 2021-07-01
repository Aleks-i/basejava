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
    private final Map<ContactType, Set<String>> contactData;
    private final Map<SectionType, Section> sectionData;

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

    public void addContactData(ContactType contactType, String... contact) {
        Set<String> newContacts = Set.of(contact);
        contactData.merge(contactType, newContacts, (a, b) -> {
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

    public Map<SectionType, Section> getSectionData() {
        return sectionData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName).append("\n").append("\n");

        for (ContactType contactType : ContactType.values()) {
            builder.append(contactType.getTitle()).append(contactData.get(contactType)).append("\n");
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
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
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

        public List<TextSection> getContent() {
            return content;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            content.forEach(c -> {
                builder.append(c.toString());
            });
            return builder.toString();
        }
    }

    public class UrlLinkSection implements Section {
        private final List<OrganizationSection> organizations;

        public UrlLinkSection() {
            this.organizations = new ArrayList<>();
        }

        public List<OrganizationSection> getOrganizations() {
            return organizations;
        }

        public void addOrganizations(List<OrganizationSection> organizations) {
            this.organizations.addAll(organizations);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            organizations.forEach(o -> builder.append(o.toString()));
            return builder.toString();
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
                    content.content.forEach(c -> builder.append(String.format("%20s", "")).append(String.format("%20s", c)).append("\n"));
                }
                builder.append("\n");
                return builder.toString();
            }
        }
    }
}
