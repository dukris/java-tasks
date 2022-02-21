package by.epam.dao.impl;

import by.epam.dao.ConnectionPool;
import by.epam.entity.RoleType;
import by.epam.entity.User;
import by.epam.exceptions.ConnectionException;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements IUserDao {
    private static final Logger logger = LogManager.getLogger(UserDao.class.getName());

    public UserDao() {
    }

    public String getEncodedPassword(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_USER_BY_EMAIL)) {
            statement.setString(ConstantsSQL.SELECT_BY_EMAIL_EMAIL_INDEX, email);
            ResultSet rs = statement.executeQuery();
            String password = "";
            while (rs.next()) {
                password = rs.getString(ConstantsSQL.SELECT_BY_EMAIL_PASSWORD_INDEX);
            }
            return password;
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_USER_BY_EMAIL)) {
            statement.setString(ConstantsSQL.SELECT_BY_EMAIL_EMAIL_INDEX, email);
            ResultSet rs = statement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getLong(ConstantsSQL.SELECT_BY_EMAIL_ID_INDEX));
                user.setName(rs.getString(ConstantsSQL.SELECT_BY_EMAIL_NAME_INDEX));
                user.setEmail(email);
                user.setPassword(rs.getString(ConstantsSQL.SELECT_BY_EMAIL_PASSWORD_INDEX));
                user.setRoleType(RoleType.valueOf(rs.getString(ConstantsSQL.SELECT_BY_EMAIL_ROLE_INDEX)));
            }
            return Optional.of(user);
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll(int limit, int offset) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            statement.setInt(ConstantsSQL.SELECT_ALL_LIMIT_INDEX, limit);
            statement.setInt(ConstantsSQL.SELECT_ALL_OFFSET_INDEX, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ConstantsSQL.SELECT_ALL_ID_INDEX));
                user.setName(rs.getString(ConstantsSQL.SELECT_ALL_NAME_INDEX));
                user.setEmail(rs.getString(ConstantsSQL.SELECT_ALL_EMAIL_INDEX));
                user.setPassword(rs.getString(ConstantsSQL.SELECT_ALL_PASSWORD_INDEX));
                user.setRoleType(RoleType.valueOf(rs.getString(ConstantsSQL.SELECT_ALL_ROLE_INDEX)));
                users.add(user);
            }
            return users;
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find all users!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_USER_BY_ID)) {
            statement.setLong(ConstantsSQL.SELECT_BY_ID_ID_INDEX, id);
            ResultSet rs = statement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(id);
                user.setName(rs.getString(ConstantsSQL.SELECT_BY_ID_NAME_INDEX));
                user.setEmail(rs.getString(ConstantsSQL.SELECT_BY_ID_EMAIL_INDEX));
                user.setPassword(rs.getString(ConstantsSQL.SELECT_BY_ID_PASSWORD_INDEX));
                user.setRoleType(RoleType.valueOf(rs.getString(ConstantsSQL.SELECT_BY_ID_ROLE_INDEX)));
            }
            return Optional.of(user);
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_DELETE_USER_BY_ID)) {
            statement.setLong(ConstantsSQL.DELETE_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to delete user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(User entity) throws DaoException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SQL_CREATE_USER)) {
            preparedStatement.setString(ConstantsSQL.NAME_CREATE_INDEX, entity.getName());
            preparedStatement.setString(ConstantsSQL.EMAIL_CREATE_INDEX, entity.getEmail());
            preparedStatement.setString(ConstantsSQL.PASSWORD_CREATE_INDEX, entity.getPassword());
            preparedStatement.setString(ConstantsSQL.ROLE_CREATE_INDEX, entity.getRoleType().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to add user!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_UPDATE_USER)) {
            statement.setString(ConstantsSQL.PASSWORD_UPDATE_INDEX, entity.getPassword());
            statement.setString(ConstantsSQL.NAME_UPDATE_INDEX, entity.getName());
            statement.setString(ConstantsSQL.EMAIL_UPDATE_INDEX, entity.getEmail());
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to update user! ");
            throw new DaoException(e.getMessage());
        }
    }
}