package by.epam.dao;

import by.epam.exceptions.ConnectionException;

import java.sql.Connection;

public interface IConnectionPool {
    Connection getConnection() throws ConnectionException;

    void initialize() throws ConnectionException;

    void releaseConnection(Connection connection) throws ConnectionException;

    void destroy() throws ConnectionException;
}
