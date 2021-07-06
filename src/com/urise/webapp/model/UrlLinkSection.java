package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class UrlLinkSection extends AbstractSection {
    private final List<OrganizationSection> content;

    public UrlLinkSection(List<OrganizationSection> content) {
        this.content = content;
    }

    public UrlLinkSection() {
        this.content = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        content.forEach(o -> builder.append(o.toString()));
        return builder.toString();
    }

    @Override
    public List<OrganizationSection> getContent() {
        return content;
    }
}
