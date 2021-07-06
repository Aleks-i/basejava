package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextSection <String> extends AbstractSection<String> {
    private final List<String> content;

    public TextSection() {
        this.content = new ArrayList<>();
    }

    public TextSection(List<String> content) {
        this.content = content;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder builder = new StringBuilder();
        for (String s : content) {
            builder.append(s).append("\n");
        }
        content.forEach(c -> builder.append(c).append("\n"));
        builder.append("\n");
        return builder.toString();
    }

    public List<String> getContent() {
        return content;
    }
}
