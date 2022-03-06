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

public class EditFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(EditFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IFlightDao flightDao = new FlightDao();
            Flight flight = (Flight) session.getAttribute(ConstantsJSP.EDIT_FLIGHT);
            flight.setFromCity(req.getParameter(ConstantsJSP.FROM));
            flight.setToCity(req.getParameter(ConstantsJSP.TO));
            flight.setDate(req.getParameter(ConstantsJSP.DATE));
            flight.setAmount(Integer.parseInt(req.getParameter(ConstantsJSP.AMOUNT)));
            flight.setPrice(Integer.parseInt(req.getParameter(ConstantsJSP.PRICE)));
            flightDao.update(flight);
            session.setAttribute(ConstantsJSP.FLIGHTS, flightDao.findAll(20, 0));
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error("Unable to edit the flight!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "Unable to edit the flight!");
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}

