package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T executeBlockCode(SqlHelperBlockCode<T> sqlHelperBlockCode, String query) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            return sqlHelperBlockCode.execute(preparedStatement);
        } catch (SQLException e) {
            throw new ExistStorageException(e);
        }
    }
}