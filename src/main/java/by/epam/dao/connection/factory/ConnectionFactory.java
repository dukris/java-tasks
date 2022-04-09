package by.epam.dao.connection.factory;

import by.epam.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    public static Connection createConnection(Properties dbProperties) throws ConnectionException {
        try {
            String dbUrl = dbProperties.getProperty(DB_URL);
            String dbUser = dbProperties.getProperty(DB_USER);
            String dbPassword = dbProperties.getProperty(DB_PASSWORD);
            Class.forName(dbProperties.getProperty(DB_DRIVER));
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            logger.error("Unable to connect to DB!");
            throw new ConnectionException(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC driver not found!");
            throw new ConnectionException(e.getMessage());
        }
    }
}
