package by.epam.dao;

import by.epam.entity.Order;
import by.epam.exceptions.DaoException;

import java.util.List;

public interface IOrderDao extends IDao<Order> {
    List<Order> findByUserId(long userId) throws DaoException;
}

