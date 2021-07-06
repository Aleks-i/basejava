package com.urise.webapp.model;

import java.time.LocalDate;

public class Position {
    private final String url;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String typeOfActivity;
    private final BulletedListSection content;

    public Position(String url, LocalDate startDate, LocalDate endDate, String typeOfActivity,
                    BulletedListSection content) {
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeOfActivity = typeOfActivity;
        this.content = content;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(url).append("\n").append("\n")
                .append(String.format("%-12s", startDate))
                .append(String.format("%-20s", endDate))
                .append(typeOfActivity).append("\n");
        if (content.getContent() != null) {
            content.getContent().forEach(c -> builder.append(String.format("%20s", ""))
                    .append(c).append("\n"));
        }
        builder.append("\n");
        return builder.toString();
    }
}
