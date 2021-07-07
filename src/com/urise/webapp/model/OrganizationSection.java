package com.urise.webapp.model;

public class OrganizationSection {
    private final String url;
    private final String timePeriod;
    private final String typeOfActivity;
    private final TextSection content;

    public OrganizationSection(String url, String timePeriod, String typeOfActivity, TextSection content) {
        this.url = url;
        this.timePeriod = timePeriod;
        this.typeOfActivity = typeOfActivity;
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(url).append("\n").append("\n")
                .append(String.format("%-20s", timePeriod))
                .append(typeOfActivity).append("\n");
        if (content.getContent() != null) {
            content.getContent().forEach(c -> builder.append(String.format("%20s", ""))
                    .append(c).append("\n"));
        }
        builder.append("\n");
        return builder.toString();
    }
}