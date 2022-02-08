package by.epam.dao.impl;


import by.epam.dao.IConnectionPool;
import by.epam.exceptions.ConnectionException;
import by.epam.utils.ConstantsSQL;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;


public class ConnectionPool implements IConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private BlockingQueue<Connection> availableConnections;
    private List<Connection> usedConnections;
    private static int poolSize;

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private ConnectionPool() {

    }

    public ConnectionPool(String url, String user, String password, BlockingQueue<Connection> availableConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.availableConnections = availableConnections;
        usedConnections = new ArrayList<>();
    }

    public ConnectionPool create() throws ConnectionException {
        ConnectionPool.load();
        BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(ConnectionPool.poolSize);
        try {
            Class.forName(driver);
            for (int i = 0; i < ConnectionPool.poolSize; i++) {
                pool.add(createConnection(ConnectionPool.url, ConnectionPool.user, ConnectionPool.password));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.warning("Unable to load DB properties!");
            throw new ConnectionException(e.getMessage());
        }
        return new ConnectionPool(ConnectionPool.url, ConnectionPool.user, ConnectionPool.password, pool);
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.warning("Unable to get connection!");
            throw new ConnectionException(e.getMessage());
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection){
        availableConnections.add(connection);
        return usedConnections.remove(connection);
    }


    private Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static void load() throws ConnectionException {
        Properties properties = new Properties();
        try (InputStream stream = ConnectionPool.class.getClassLoader().getResourceAsStream(ConstantsSQL.DB_PROPERTIES)) {
            properties.load(stream);
            driver = properties.getProperty(ConstantsSQL.DRIVER_PROPERTY);
            user = properties.getProperty(ConstantsSQL.USER_PROPERTY);
            password = properties.getProperty(ConstantsSQL.PASSWORD_PROPERTY);
            url = properties.getProperty(ConstantsSQL.URL_PROPERTY);
            poolSize = Integer.parseInt(properties.getProperty(ConstantsSQL.POOL_SIZE_PROPERTY));
        } catch (IOException | NumberFormatException e) {
            logger.warning("Unable to load DB properties!");
            throw new ConnectionException(e.getMessage());
        }
    }

    @Override
    public void close() throws ConnectionException {
        try{
            usedConnections.forEach(this::releaseConnection);
            for (Connection connection : availableConnections) {
                connection.close();
            }
            availableConnections.clear();
        }catch (SQLException e){
            logger.warning("Unable to close all connections!");
            throw new ConnectionException(e.getMessage());
        }
    }

    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
