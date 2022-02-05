package by.epam.dao;


import org.apache.maven.wagon.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/users?serverTimezone=Europe/Minsk";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;
    private Statement statement;

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    public ConnectionPool() {
    }

    public void initialize() throws ConnectionException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            logger.warning("Unable to connect to DB!");
            throw new ConnectionException(e.getMessage(), e);
        }
        logger.info("Connection pool initialized");
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() throws ConnectionException {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.warning("Unable to close the DB!");
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
