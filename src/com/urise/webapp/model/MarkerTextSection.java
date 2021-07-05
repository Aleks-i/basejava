package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class MarkerTextSection<TextSection> implements Section<TextSection> {
    private final List<TextSection> content;

    public MarkerTextSection() {
        this.content = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(c -> builder.append(c.toString()));
        return builder.toString();
    }

    public List<TextSection> getContent() {
        return content;
    }
}
