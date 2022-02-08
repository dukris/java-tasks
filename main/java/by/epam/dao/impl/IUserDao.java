package by.epam.dao.impl;

import by.epam.dao.IDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;

import java.util.Optional;

public interface IUserDao extends IDao<User> {
    Optional<User> findByEmail(String email) throws DaoException;
}
