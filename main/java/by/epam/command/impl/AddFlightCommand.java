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

public class AddFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(AddFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IFlightDao flightDao = new FlightDao();
            String from = req.getParameter(ConstantsJSP.FROM);
            String to = req.getParameter(ConstantsJSP.TO);
            String date = req.getParameter(ConstantsJSP.DATE);
            int amount = Integer.parseInt(req.getParameter(ConstantsJSP.AMOUNT));
            int price = Integer.parseInt(req.getParameter(ConstantsJSP.PRICE));
            Flight flight = new Flight(from, to, date, amount, price);
            flightDao.create(flight);
            session.setAttribute(ConstantsJSP.FLIGHTS, flightDao.findAll(20,0));
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            req.setAttribute(ConstantsJSP.ERROR_NAME, "Unable to add the flight!");
            return new CommandResult(ConstantsJSP.ADD_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}
