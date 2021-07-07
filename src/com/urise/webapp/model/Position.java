package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
    private String getEndDate(LocalDate endDate) {
        return endDate.equals(LocalDate.now()) ? "Сейчас" : endDate.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(url).append("\n").append("\n")
                .append(startDate.format(dateTimeFormatter))
                .append("-")
                .append(String.format("%-12s", getEndDate(endDate)))
                .append(typeOfActivity).append("\n");
        if (content.getContent() != null) {
            content.getContent().forEach(c -> builder.append(String.format("%20s", ""))
                    .append(c));
        }
        builder.append("\n");
        return builder.toString();
    }
}