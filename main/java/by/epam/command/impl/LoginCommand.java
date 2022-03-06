package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.IOrderDao;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.dao.IUserDao;
import by.epam.dao.impl.OrderDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.*;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IUserDao userDao = new UserDao();
            IFlightDao flightDao = new FlightDao();
            IOrderDao orderDao = new OrderDao();
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent() && DigestUtils.sha256Hex(password).equals(user.get().getPassword())) {
                session.setAttribute(ConstantsJSP.USER, user.get());
                List<Order> orders = orderDao.findByUserId(user.get().getId());
                List<OrderInfo> infos = new ArrayList<>();
                for (Order ord: orders){
                    Optional<Flight> optionalFlight = flightDao.findById(ord.getFlightId());
                    optionalFlight.ifPresent(value -> infos.add(new OrderInfo(ord, value)));
                }
                session.setAttribute(ConstantsJSP.ORDERS, infos);
                if (user.get().getRoleType() == RoleType.ADMIN) {
                    List<Flight> flights = flightDao.findAll(20, 0);
                    List<User> users = userDao.findAll(20, 0);
                    users.removeIf(us -> us.getRoleType() == RoleType.ADMIN);
                    session.setAttribute(ConstantsJSP.USERS, users);
                    session.setAttribute(ConstantsJSP.FLIGHTS, flights);
                }
                return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
            } else {
                logger.warn("Invalid Username or Password!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, "Invalid Username or Password!");
                return new CommandResult(ConstantsJSP.LOGIN_PAGE_URL, CommandResultType.FORWARD);
            }
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new CommandResult(ConstantsJSP.ERROR_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}
