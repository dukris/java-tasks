package by.epam.dao;

import by.epam.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool implements IConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());

    private static final String POOL_SIZE = "db.default-pool-size";
    private static final String DB_CONNECTION_PATH = "db.properties";

    private BlockingQueue<Connection> availableConnections;
    private List<Connection> usedConnections;

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private ConnectionPool() {

    }

    @Override
    public void initialize() throws ConnectionException {
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));
            int poolSize = Integer.parseInt(dbProperties.getProperty(POOL_SIZE));
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayList<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = ConnectionFactory.createConnection(dbProperties);
                availableConnections.add(connection);
            }
        } catch (IOException e) {
            logger.error("Unable to load DB properties!");
            throw new ConnectionException(e.getMessage());
        }
    }

    @Override
    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection != null) {
            usedConnections.remove(connection);
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Unable to release connection!");
                throw new ConnectionException(e.getMessage());
            }
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        Connection connection;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.error("Unable to get connection!");
            throw new ConnectionException(e.getMessage());
        }
        return connection;
    }

    @Override
    public void destroy() throws ConnectionException {
        try {
            for (Connection connection : availableConnections) {
                connection.close();
            }
            for (Connection connection : usedConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Unable to close all connections!");
            throw new ConnectionException(e.getMessage());
        }
    }

    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
