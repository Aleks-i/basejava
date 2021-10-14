package com.urise.webapp.sql;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface SqlHelperBlockCode<T> {
    T execute(PreparedStatement preparedStatement);
}