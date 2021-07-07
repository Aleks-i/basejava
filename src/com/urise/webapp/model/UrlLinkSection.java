package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class UrlLinkSection extends AbstractSection {

    public UrlLinkSection() {
        super(new ArrayList<>());
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