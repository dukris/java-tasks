package by.epam.dao;

import by.epam.entity.User;
import by.epam.exceptions.DaoException;

import org.apache.maven.wagon.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao extends AbstractDao<Integer, User> {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    public static final String SQL_CREATE_USER = "INSERT INTO users VALUES(?, ?, ?, ?)";

    public UserDao() {
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initialize();
            Statement st = connectionPool.getStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            connectionPool.close();
        } catch (ConnectionException | SQLException e) {
            logger.warning("Request or table failed!");
            throw new DaoException(e.getMessage(), e);
        }
        return users;
    }

    public User findUserByEmail(String email) throws DaoException {
        User user= new User();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initialize();
            Connection cn = connectionPool.getConnection();
            PreparedStatement st = cn.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(email);
            user.setPassword(resultSet.getString("password"));
            connectionPool.close();
        } catch (SQLException | ConnectionException e) {
            logger.warning("Request or table failed!");
            throw new DaoException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user;
        try {
            user = new User();
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initialize();
            Connection cn = connectionPool.getConnection();
            PreparedStatement st = cn.prepareStatement(SQL_SELECT_USER_BY_ID);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setId(id);
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            connectionPool.close();
        } catch (SQLException | ConnectionException e) {
            logger.warning("Request or table failed!");
            throw new DaoException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initialize();
            Connection cn = connectionPool.getConnection();
            PreparedStatement st = cn.prepareStatement(SQL_DELETE_USER_BY_ID);
            st.setInt(1, id);
            st.execute();
            connectionPool.close();
        } catch (SQLException | ConnectionException e) {
            logger.warning("Request or table failed!");
            return false;
        }
        return true;
    }

    @Override
    public boolean create(User entity) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initialize();
            Connection cn = connectionPool.getConnection();
            PreparedStatement st = cn.prepareStatement(SQL_CREATE_USER);
            st.setInt(1, entity.getId());
            st.setString(2, entity.getName());
            st.setString(3, entity.getEmail());
            st.setString(4, entity.getPassword());
            st.execute();
            connectionPool.close();
        } catch (SQLException | ConnectionException e) {
            logger.warning("This email is already taken!");
            return false;
        }
        return true;
    }
}