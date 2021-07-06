package com.urise.webapp.model;

import java.util.List;

public abstract class AbstractSection<T> {
    protected List<T> content;

    public AbstractSection(List<T> content) {
        this.content = content;
    }

    abstract List<T> getContent();
}
