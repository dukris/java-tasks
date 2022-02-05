package by.epam.dao;

import by.epam.entity.Entity;
import by.epam.exceptions.DaoException;

import java.util.List;

public abstract class AbstractDao<K, T extends Entity> {
    public abstract List<T> findAll() throws DaoException;

    public abstract T findEntityById(K id) throws DaoException;

    public abstract boolean delete(K id);

    public abstract boolean create(T entity);

}
