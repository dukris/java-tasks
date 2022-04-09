package by.epam.service;

import by.epam.dao.IFlightDao;
import by.epam.dao.IUserDao;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.*;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.utils.ConstantsJSP;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class.getName());

    public static void updateUser(HttpSession session, String password) throws ServiceException {
        try {
            IUserDao userDao = new UserDao();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            user.setPassword(DigestUtils.sha256Hex(password));
            userDao.update(user);
            session.setAttribute(ConstantsJSP.USER, user);
        } catch (DaoException e) {
            logger.error("Unable to set admin attributes!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void registerUser(HttpSession session, String name, String surname, String passport, String email, String password) throws ServiceException {
        try {
            IUserDao userDao = new UserDao();
            User user = new User(name, surname, passport, email, password, RoleType.USER);
            userDao.create(user);
            session.setAttribute(ConstantsJSP.USER, user);
        } catch (DaoException e) {
            logger.error("Unable to register user!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static Optional<User> findUser(String email) throws ServiceException {
        try {
            IUserDao userDao = new UserDao();
            System.out.println("find user");
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            logger.error("Unable to find user!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void clearUserAttributes(HttpSession session) {
        session.removeAttribute(ConstantsJSP.USER);
        session.removeAttribute(ConstantsJSP.USERS);
        session.removeAttribute(ConstantsJSP.FLIGHTS);
        session.removeAttribute(ConstantsJSP.ORDER);
        session.removeAttribute(ConstantsJSP.ORDERS);
    }

    public static boolean isValidForLogin(Optional<User> user, String password) {
        return user.isPresent() && DigestUtils.sha256Hex(password).equals(user.get().getPassword());
    }

    public static boolean isValidForUpdate(HttpSession session, String password, String passwordOld, String passwordConfirm) throws ServiceException {
        try {
            IUserDao userDao = new UserDao();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            return DigestUtils.sha256Hex(passwordOld).equals(userDao.getEncodedPassword(user.getEmail())) && password.equals(passwordConfirm);
        } catch (DaoException e) {
            logger.error("Unable to update user!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void setCities(HttpSession session) throws ServiceException {
        try {
            IFlightDao flightDao = new FlightDao();
            List<String> cities;
            if (ConstantsJSP.EN.equals(session.getAttribute(ConstantsJSP.LANGUAGE))) {
                cities = flightDao.findCitiesEn(ConstantsJSP.LIMIT, ConstantsJSP.OFFSET);
            } else {
                cities = flightDao.findCitiesRu(ConstantsJSP.LIMIT, ConstantsJSP.OFFSET);
            }
            session.setAttribute(ConstantsJSP.CITIES, cities);
        } catch (DaoException e) {
            logger.error("Unable to set cities!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void setAdminAttributes(HttpSession session) throws ServiceException {
        try {
            IUserDao userDao = new UserDao();
            IFlightDao flightDao = new FlightDao();
            List<Flight> flights = flightDao.findAll(ConstantsJSP.LIMIT, ConstantsJSP.OFFSET);
            List<User> users = userDao.findAll(ConstantsJSP.LIMIT, ConstantsJSP.OFFSET);
            users.removeIf(us -> us.getRoleType() == RoleType.ADMIN);
            session.setAttribute(ConstantsJSP.USERS, users);
            session.setAttribute(ConstantsJSP.FLIGHTS, flights);
        } catch (DaoException e) {
            logger.error("Unable to set admin attributes!");
            throw new ServiceException(e.getMessage());
        }
    }
}
