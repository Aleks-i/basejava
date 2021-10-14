package com.urise.webapp.sql;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface BlockCode {
    <T> T execute(PreparedStatement preparedStatement);
}
