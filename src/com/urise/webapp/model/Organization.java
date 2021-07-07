package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization implements Section {
    private final List<Position> content;

    public Organization() {
        this.content = new ArrayList<>();
    }

    public List<Position> getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(o -> builder.append(o.toString()));
        return builder.toString();
    }
}