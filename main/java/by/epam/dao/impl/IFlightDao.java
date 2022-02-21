package by.epam.dao.impl;

import by.epam.dao.IDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;

import java.util.List;

public interface IFlightDao extends IDao<Flight> {
    List<Flight> findByParameters(String from, String to) throws DaoException;

    List<Flight> findByAllParameters(String from, String to, String date) throws DaoException;
}
