package com.urise.webapp.util;

import java.io.IOException;

@FunctionalInterface
public interface Writer<T> {
    void write(T t) throws IOException;
}