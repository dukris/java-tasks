package by.epam.dao;


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


public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private static final String DB_PROPERTIES = "db.properties";
    private static final String USER_PROPERTY = "dataSource.user";
    private static final String PASSWORD_PROPERTY = "dataSource.password";
    private static final String URL_PROPERTY = "datasource.jdbcUrl";
    private static final String POOL_SIZE_PROPERTY = "dataSource.maxPoolSize";
    private static final String DRIVER_PROPERTY = "datasource.driverClassName";

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private BlockingQueue<Connection> availableConnections;
    private List<Connection> usedConnections;
    private static int poolSize;

    public ConnectionPool(String url, String user, String password, BlockingQueue<Connection> availableConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.availableConnections = availableConnections;
        usedConnections = new ArrayList<>();
    }

    public static ConnectionPool create() {
        ConnectionPool.loadProperties();
        BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(ConnectionPool.poolSize);
        try {
            Class.forName(driver);
            for (int i = 0; i < ConnectionPool.poolSize; i++) {
                pool.add(createConnection(ConnectionPool.url, ConnectionPool.user, ConnectionPool.password));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.warning("Unable to load DB properties!");
        }
        return new ConnectionPool(ConnectionPool.url, ConnectionPool.user, ConnectionPool.password, pool);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
        } catch (InterruptedException e) {
            logger.warning("Unable to get connection!");
        }
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        availableConnections.add(connection);
        return usedConnections.remove(connection);
    }


    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ConnectionPool.class.getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
            properties.load(stream);
            driver = properties.getProperty(DRIVER_PROPERTY);
            user = properties.getProperty(USER_PROPERTY);
            password = properties.getProperty(PASSWORD_PROPERTY);
            url = properties.getProperty(URL_PROPERTY);
            poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE_PROPERTY));
        } catch (IOException | NumberFormatException e) {
            logger.warning("Unable to load DB properties!");
        }
    }

    public int getSize() {
        return availableConnections.size() + usedConnections.size();
    }

    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection connection : availableConnections) {
            connection.close();
        }
        availableConnections.clear();
    }
}
