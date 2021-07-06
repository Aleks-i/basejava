package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class UrlLinkSection <OrganizationSection> extends AbstractSection<OrganizationSection> {
    private final List<OrganizationSection> content;

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
