package by.epam.dao.impl;

import by.epam.dao.IOrderDao;
import by.epam.dao.connection.impl.ConnectionPool;
import by.epam.entity.Order;
import by.epam.exceptions.ConnectionException;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.service.OrderService;
import by.epam.utils.ConstantsSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements IOrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDao.class.getName());

    @Override
    public List<Order> findAll(int limit, int offset) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_ALL_ORDERS)) {
            List<Order> orders = new ArrayList<>();
            statement.setInt(ConstantsSQL.SELECT_ALL_LIMIT_INDEX, limit);
            statement.setInt(ConstantsSQL.SELECT_ALL_OFFSET_INDEX, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(ConstantsSQL.SELECT_ALL_ID_INDEX));
                order.setUserId(rs.getLong(ConstantsSQL.SELECT_ALL_USER_ID_INDEX));
                order.setFlightId(rs.getLong(ConstantsSQL.SELECT_ALL_FLIGHT_ID_INDEX));
                order.setAmount(rs.getInt(ConstantsSQL.SELECT_ALL_TICKETS_AMOUNT_INDEX));
                order.setTotalCost(OrderService.calculateTotalCost(order));
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ConnectionException | ServiceException e) {
            logger.error("Unable to find all orders!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_ORDER_BY_ID)) {
            statement.setLong(ConstantsSQL.SELECT_BY_ID_ID_INDEX, id);
            ResultSet rs = statement.executeQuery();
            Order order = new Order();
            while (rs.next()) {
                order.setId(id);
                order.setUserId(rs.getLong(ConstantsSQL.SELECT_BY_ID_USER_ID_INDEX));
                order.setFlightId(rs.getLong(ConstantsSQL.SELECT_BY_ID_FLIGHT_ID_INDEX));
                order.setAmount(rs.getInt(ConstantsSQL.SELECT_BY_ID_TICKETS_AMOUNT_INDEX));
                order.setTotalCost(OrderService.calculateTotalCost(order));
            }
            return Optional.of(order);
        } catch (SQLException | ConnectionException | ServiceException e) {
            logger.error("Unable to find order!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_DELETE_ORDER_BY_ID)) {
            statement.setLong(ConstantsSQL.DELETE_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to delete order!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(Order entity) throws DaoException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SQL_CREATE_ORDER)) {
            preparedStatement.setLong(ConstantsSQL.USER_ID_CREATE_INDEX, entity.getUserId());
            preparedStatement.setLong(ConstantsSQL.FLIGHT_ID_CREATE_INDEX, entity.getFlightId());
            preparedStatement.setInt(ConstantsSQL.TICKETS_AMOUNT_CREATE_INDEX, entity.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to add order!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Order entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_UPDATE_ORDER)) {
            preparedStatement.setLong(ConstantsSQL.USER_ID_UPDATE_INDEX, entity.getUserId());
            preparedStatement.setLong(ConstantsSQL.FLIGHT_ID_UPDATE_INDEX, entity.getFlightId());
            preparedStatement.setInt(ConstantsSQL.TICKETS_AMOUNT_UPDATE_INDEX, entity.getAmount());
            preparedStatement.setLong(ConstantsSQL.ID_ID_UPDATE_INDEX, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to update order!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Order> findByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_ORDER_BY_USER_ID)) {
            List<Order> orders = new ArrayList<>();
            statement.setLong(ConstantsSQL.SELECT_BY_USER_ID_USER_ID_INDEX, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(ConstantsSQL.SELECT_BY_USER_ID_ID_INDEX));
                order.setUserId(userId);
                order.setFlightId(rs.getLong(ConstantsSQL.SELECT_BY_USER_ID_FLIGHT_ID_INDEX));
                order.setAmount(rs.getInt(ConstantsSQL.SELECT_BY_USER_ID_TICKETS_AMOUNT_INDEX));
                order.setTotalCost(OrderService.calculateTotalCost(order));
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ConnectionException | ServiceException e) {
            logger.error("Unable to find all orders!");
            throw new DaoException(e.getMessage());
        }
    }
}
