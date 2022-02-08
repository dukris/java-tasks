package by.epam.dao;

import by.epam.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
    Connection getConnection() throws ConnectionException;
    boolean releaseConnection(Connection connection) throws ConnectionException;
    void close() throws ConnectionException;
}
