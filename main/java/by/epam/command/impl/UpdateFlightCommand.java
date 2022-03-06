package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UpdateFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(UpdateFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IFlightDao flightDao = new FlightDao();
            String deleteChoice = req.getParameter(ConstantsJSP.DELETE_CHOICE);
            String editChoice = req.getParameter(ConstantsJSP.EDIT_CHOICE);
            if (deleteChoice != null) {
                flightDao.delete(Long.parseLong(deleteChoice));
                session.setAttribute(ConstantsJSP.FLIGHTS, flightDao.findAll(20, 0));
            }
            if (editChoice != null) {
                Optional<Flight> optionalFlight = flightDao.findById(Long.parseLong(editChoice));
                optionalFlight.ifPresent(flight -> session.setAttribute(ConstantsJSP.EDIT_FLIGHT, flight));
                return new CommandResult(ConstantsJSP.EDIT_FLIGHT_PAGE_URL, CommandResultType.FORWARD);
            }
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error("Unable to delete the flight!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "Unable to delete the flight!");
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}

