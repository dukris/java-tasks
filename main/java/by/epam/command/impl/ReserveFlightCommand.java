package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.entity.Flight;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ReserveFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(ReserveFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IFlightDao flightDao = new FlightDao();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            if (user == null) {
                session.removeAttribute(ConstantsJSP.FLIGHTS);
                req.setAttribute(ConstantsJSP.ERROR_NAME, "To purchase tickets, please log in.");
                return new CommandResult(ConstantsJSP.LOGIN_PAGE_URL, CommandResultType.FORWARD);
            } else {
                String id = req.getParameter(ConstantsJSP.ID);
                Optional<Flight> optionalFlight = flightDao.findById(Long.parseLong(id));
                optionalFlight.ifPresent(flight -> session.setAttribute(ConstantsJSP.ORDER, flight));
                return new CommandResult(ConstantsJSP.RESERVE_PAGE_URL, CommandResultType.FORWARD);
            }
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new CommandResult(ConstantsJSP.ERROR_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}

