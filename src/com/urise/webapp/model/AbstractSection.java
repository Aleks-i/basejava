package com.urise.webapp.model;

import java.util.List;

public abstract class AbstractSection<T> {
    abstract List<T> getContent();
}
