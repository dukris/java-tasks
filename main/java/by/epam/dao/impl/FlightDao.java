package by.epam.dao.impl;

import by.epam.dao.IFlightDao;
import by.epam.dao.connection.impl.ConnectionPool;
import by.epam.entity.Flight;
import by.epam.exceptions.ConnectionException;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements IFlightDao {
    private static final Logger logger = LogManager.getLogger(FlightDao.class.getName());

    public FlightDao() {

    }

    @Override
    public List<Flight> findAll(int limit, int offset) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_ALL_FLIGHTS)) {
            List<Flight> flights = new ArrayList<>();
            statement.setInt(ConstantsSQL.SELECT_ALL_LIMIT_INDEX, limit);
            statement.setInt(ConstantsSQL.SELECT_ALL_OFFSET_INDEX, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getLong(ConstantsSQL.SELECT_ALL_ID_INDEX));
                flight.setFromCity(rs.getString(ConstantsSQL.SELECT_ALL_FROM_INDEX));
                flight.setToCity(rs.getString(ConstantsSQL.SELECT_ALL_TO_INDEX));
                flight.setDate(rs.getString(ConstantsSQL.SELECT_ALL_DATE_INDEX));
                flight.setAmount(rs.getInt(ConstantsSQL.SELECT_ALL_AMOUNT_INDEX));
                flight.setPrice(rs.getInt(ConstantsSQL.SELECT_ALL_PRICE_INDEX));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find all flights!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Flight> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_FLIGHT_BY_ID)) {
            statement.setLong(ConstantsSQL.SELECT_BY_ID_ID_INDEX, id);
            ResultSet rs = statement.executeQuery();
            Flight flight = new Flight();
            while (rs.next()) {
                flight.setId(id);
                flight.setFromCity(rs.getString(ConstantsSQL.SELECT_BY_ID_FROM_INDEX));
                flight.setToCity(rs.getString(ConstantsSQL.SELECT_BY_ID_TO_INDEX));
                flight.setDate(rs.getString(ConstantsSQL.SELECT_BY_ID_DATE_INDEX));
                flight.setAmount(rs.getInt(ConstantsSQL.SELECT_BY_ID_AMOUNT_INDEX));
                flight.setPrice(rs.getInt(ConstantsSQL.SELECT_BY_ID_PRICE_INDEX));
            }
            return Optional.of(flight);
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find flight!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_DELETE_FLIGHT_BY_ID)) {
            statement.setLong(ConstantsSQL.DELETE_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to delete flight!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(Flight entity) throws DaoException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SQL_CREATE_FLIGHT)) {
            preparedStatement.setString(ConstantsSQL.FROM_CREATE_INDEX, entity.getFromCity());
            preparedStatement.setString(ConstantsSQL.TO_CREATE_INDEX, entity.getToCity());
            preparedStatement.setString(ConstantsSQL.DATE_CREATE_INDEX, entity.getDate());
            preparedStatement.setInt(ConstantsSQL.AMOUNT_CREATE_INDEX, entity.getAmount());
            preparedStatement.setInt(ConstantsSQL.PRICE_CREATE_INDEX, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to add flight!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Flight entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ConstantsSQL.SQL_UPDATE_FLIGHT)) {
            preparedStatement.setString(ConstantsSQL.FROM_UPDATE_INDEX, entity.getFromCity());
            preparedStatement.setString(ConstantsSQL.TO_UPDATE_INDEX, entity.getToCity());
            preparedStatement.setString(ConstantsSQL.DATE_UPDATE_INDEX, entity.getDate());
            preparedStatement.setInt(ConstantsSQL.AMOUNT_UPDATE_INDEX, entity.getAmount());
            preparedStatement.setInt(ConstantsSQL.PRICE_UPDATE_INDEX, entity.getPrice());
            preparedStatement.setLong(ConstantsSQL.ID_UPDATE_INDEX, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to update flight!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Flight> findByAllParameters(String from, String to, String date) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_FLIGHT_BY_PARAM)) {
            List<Flight> flights = new ArrayList<>();
            statement.setString(ConstantsSQL.SELECT_BY_PARAM_FROM_INDEX, from);
            statement.setString(ConstantsSQL.SELECT_BY_PARAM_TO_INDEX, to);
            statement.setString(ConstantsSQL.SELECT_BY_PARAM_DATE_INDEX, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getLong(ConstantsSQL.SELECT_BY_PARAM_ID_INDEX));
                flight.setFromCity(from);
                flight.setToCity(to);
                flight.setDate(date);
                flight.setAmount(rs.getInt(ConstantsSQL.SELECT_BY_PARAM_AMOUNT_INDEX));
                flight.setPrice(rs.getInt(ConstantsSQL.SELECT_BY_PARAM_PRICE_INDEX));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find flights!");
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Flight> findByParameters(String from, String to) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ConstantsSQL.SQL_SELECT_FLIGHT_BY_PARAM_NO_DATE)) {
            List<Flight> flights = new ArrayList<>();
            statement.setString(ConstantsSQL.SELECT_BY_PARAM_FROM_INDEX, from);
            statement.setString(ConstantsSQL.SELECT_BY_PARAM_TO_INDEX, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getLong(ConstantsSQL.SELECT_BY_PARAM_ID_INDEX));
                flight.setFromCity(from);
                flight.setToCity(to);
                flight.setDate(rs.getString(ConstantsSQL.SELECT_BY_PARAM_DATE_INDEX_NO_DATE));
                flight.setAmount(rs.getInt(ConstantsSQL.SELECT_BY_PARAM_AMOUNT_INDEX));
                flight.setPrice(rs.getInt(ConstantsSQL.SELECT_BY_PARAM_PRICE_INDEX));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException | ConnectionException e) {
            logger.error("Unable to find flights!");
            throw new DaoException(e.getMessage());
        }
    }

}
