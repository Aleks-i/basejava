package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class BulletedListSection implements Section{
    private final List<TextSection> content;

    public BulletedListSection() {
        this.content = new ArrayList<>();
    }

    public BulletedListSection(List<TextSection> content) {
        this.content = content;
    }

    public List<TextSection> getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(c -> builder.append(c.toString()));
        return builder.toString();
    }
}
