package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextSection<String> implements Section<String> {
    private List<String> content = new ArrayList<>();

    public TextSection() {
    }

    public TextSection(List<String> content) {
        this.content = content;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(c -> builder.append(c).append("\n"));
        builder.append("\n");
        return builder.toString();
    }

    public List<String> getContent() {
        return content;
    }
}
