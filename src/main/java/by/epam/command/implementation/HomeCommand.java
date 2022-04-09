package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ErrorCommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.service.FlightService;
import by.epam.service.UserService;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomeCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(HomeCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String from = req.getParameter(ConstantsJSP.FROM);
            String to = req.getParameter(ConstantsJSP.TO);
            String date = req.getParameter(ConstantsJSP.DATE);
            if (!"".equals(date) && !FlightService.checkDate(date) || from.equals(to)) {
                logger.warn("Departure city and destination city are the same or incorrect date!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.SECOND_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.HOME_PAGE_URL);
            }
            List<Flight> flights = FlightService.findFlights(from, to, date);
            if (!flights.isEmpty()) {
                session.setAttribute(ConstantsJSP.FLIGHTS, flights);
            } else {
                logger.warn("Nothing found!");
                session.removeAttribute(ConstantsJSP.FLIGHTS);
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
            }
            return new ForwardCommandResult(ConstantsJSP.HOME_PAGE_URL);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return new ErrorCommandResult();
        }
    }
}
