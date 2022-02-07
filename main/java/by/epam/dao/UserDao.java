package by.epam.dao;

import by.epam.entity.User;
import by.epam.exceptions.DaoException;

import by.epam.utils.ConstantsJSP;
import org.apache.maven.wagon.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao implements IDao<User> {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    public static final String SQL_SELECT_ALL_USERS = "SELECT id,name,email,password FROM users LIMIT ? OFFSET ?";
    private static final int SELECT_ALL_LIMIT_INDEX = 1;
    private static final int SELECT_ALL_OFFSET_INDEX = 2;
    private static final int SELECT_ALL_ID_INDEX = 1;
    private static final int SELECT_ALL_NAME_INDEX = 2;
    private static final int SELECT_ALL_EMAIL_INDEX = 3;
    private static final int SELECT_ALL_PASSWORD_INDEX = 4;

    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id,name,password FROM users WHERE email=?";
    private static final int SELECT_BY_EMAIL_EMAIL_INDEX = 1;
    private static final int SELECT_BY_EMAIL_ID_INDEX = 1;
    private static final int SELECT_BY_EMAIL_NAME_INDEX = 2;
    private static final int SELECT_BY_EMAIL_PASSWORD_INDEX = 3;

    public static final String SQL_SELECT_USER_BY_ID = "SELECT id,name,email,password FROM users WHERE id=?";
    private static final int SELECT_BY_ID_ID_INDEX = 1;
    private static final int SELECT_BY_ID_NAME_INDEX = 1;
    private static final int SELECT_BY_ID_EMAIL_INDEX = 2;
    private static final int SELECT_BY_ID_PASSWORD_INDEX = 3;

    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final int DELETE_INDEX = 1;

    public static final String SQL_CREATE_USER = "INSERT users(name,email,password) VALUES (?,?,?)";
    private static final int NAME_CREATE_INDEX = 1;
    private static final int EMAIL_CREATE_INDEX = 2;
    private static final int PASSWORD_CREATE_INDEX = 3;

    public static final String SQL_UPDATE_USER = "UPDATE users SET password=?,name=? WHERE email=?";
    private static final int PASSWORD_UPDATE_INDEX = 1;
    private static final int NAME_UPDATE_INDEX = 2;
    private static final int EMAIL_UPDATE_INDEX = 3;

    private ConnectionPool connectionPool;

    public UserDao() {
        connectionPool = ConnectionPool.create();
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)) {
            statement.setString(SELECT_BY_EMAIL_EMAIL_INDEX, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(SELECT_BY_EMAIL_ID_INDEX));
                user.setName(rs.getString(SELECT_BY_EMAIL_NAME_INDEX));
                user.setEmail(email);
                user.setPassword(rs.getString(SELECT_BY_EMAIL_PASSWORD_INDEX));
            }
        } catch (SQLException e) {
            logger.warning("Unable to find user!");
        }
        return user;
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
            statement.setInt(SELECT_ALL_LIMIT_INDEX, limit);
            statement.setInt(SELECT_ALL_OFFSET_INDEX, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(SELECT_ALL_ID_INDEX));
                user.setName(rs.getString(SELECT_ALL_NAME_INDEX));
                user.setEmail(rs.getString(SELECT_ALL_EMAIL_INDEX));
                user.setPassword(rs.getString(SELECT_ALL_PASSWORD_INDEX));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.warning("Unable to find all users!");
        }
        return users;
    }

    @Override
    public User findById(long id) {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            statement.setLong(SELECT_BY_ID_ID_INDEX, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(id);
                user.setName(rs.getString(SELECT_BY_ID_NAME_INDEX));
                user.setEmail(rs.getString(SELECT_BY_ID_EMAIL_INDEX));
                user.setPassword(rs.getString(SELECT_BY_ID_PASSWORD_INDEX));
            }
        } catch (SQLException e) {
            logger.warning("Unable to find user!");
        }
        return user;
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setLong(DELETE_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Unable to delete user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(User entity) throws DaoException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(NAME_CREATE_INDEX, entity.getName());
            preparedStatement.setString(EMAIL_CREATE_INDEX, entity.getEmail());
            preparedStatement.setString(PASSWORD_CREATE_INDEX, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Unable to add user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(PASSWORD_UPDATE_INDEX, entity.getPassword());
            statement.setString(NAME_UPDATE_INDEX, entity.getName());
            statement.setString(EMAIL_UPDATE_INDEX, entity.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Unable to update user!");
            throw new DaoException(e.getMessage());

        }
    }


//    public UserDao() {
//    }
//
//    @Override
//    public List<User> findAll() throws DaoException {
//        List<User> users = new ArrayList<>();
//        try {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connectionPool.initialize();
//            Statement st = connectionPool.getStatement();
//            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                users.add(user);
//            }
//            connectionPool.close();
//        } catch (ConnectionException | SQLException e) {
//            logger.warning("Request or table failed!");
//            throw new DaoException(e.getMessage(), e);
//        }
//        return users;
//    }
//
//    public User findUserByEmail(String email) throws DaoException {
//        User user= new User();
//        try {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connectionPool.initialize();
//            Connection cn = connectionPool.getConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
//            st.setString(1, email);
//            ResultSet resultSet = st.executeQuery();
//            resultSet.next();
//            user.setId(resultSet.getInt("id"));
//            user.setName(resultSet.getString("name"));
//            user.setEmail(email);
//            user.setPassword(resultSet.getString("password"));
//            connectionPool.close();
//        } catch (SQLException | ConnectionException e) {
//            logger.warning("Request or table failed!");
//            throw new DaoException(e.getMessage(), e);
//        }
//        return user;
//    }
//
//    @Override
//    public User findEntityById(Integer id) throws DaoException {
//        User user;
//        try {
//            user = new User();
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connectionPool.initialize();
//            Connection cn = connectionPool.getConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_SELECT_USER_BY_ID);
//            st.setInt(1, id);
//            ResultSet resultSet = st.executeQuery();
//            resultSet.next();
//            user.setId(id);
//            user.setName(resultSet.getString("name"));
//            user.setEmail(resultSet.getString("email"));
//            user.setPassword(resultSet.getString("password"));
//            connectionPool.close();
//        } catch (SQLException | ConnectionException e) {
//            logger.warning("Request or table failed!");
//            throw new DaoException(e.getMessage(), e);
//        }
//        return user;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        try {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connectionPool.initialize();
//            Connection cn = connectionPool.getConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_DELETE_USER_BY_ID);
//            st.setInt(1, id);
//            st.execute();
//            connectionPool.close();
//        } catch (SQLException | ConnectionException e) {
//            logger.warning("Request or table failed!");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean create(User entity) {
//        try {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            connectionPool.initialize();
//            Connection cn = connectionPool.getConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_CREATE_USER);
//            st.setInt(1, entity.getId());
//            st.setString(2, entity.getName());
//            st.setString(3, entity.getEmail());
//            st.setString(4, entity.getPassword());
//            st.execute();
//            connectionPool.close();
//        } catch (SQLException | ConnectionException e) {
//            logger.warning("This email is already taken!");
//            return false;
//        }
//        return true;
//    }
}