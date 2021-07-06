package com.urise.webapp.model;

import java.util.List;

public abstract class AbstractSection {
    abstract <T> List<T> getContent();
}
