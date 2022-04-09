package by.epam.dao;

import by.epam.dao.IDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;

import java.util.List;

public interface IFlightDao extends IDao<Flight> {

    List<Flight> findByParameters(String from, String to, String date) throws DaoException;

    List<String> findCitiesEn(int limit, int offset) throws DaoException;

    List<String> findCitiesRu(int limit, int offset) throws DaoException;
}
