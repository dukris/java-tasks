package by.epam.dao;

import by.epam.exceptions.DaoException;

import java.util.List;

public interface IDao<T> {
    List<T> findAll(int limit, int offset);

    T findByEmail(String email);

    T findById(long id);

    void delete(long id) throws DaoException;

    void create(T entity) throws DaoException;

    void update(T entity) throws DaoException;
}
