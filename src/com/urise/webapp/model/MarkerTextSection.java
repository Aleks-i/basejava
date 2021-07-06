package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class MarkerTextSection extends AbstractSection {
    private final List<TextSection> content;

    public MarkerTextSection() {
        this.content = new ArrayList<>();
    }

    public MarkerTextSection(List<TextSection> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(c -> builder.append(c.toString()));
        return builder.toString();
    }

    @Override
    public List<TextSection> getContent() {
        return content;
    }
}
