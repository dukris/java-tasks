package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.impl.IFlightDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;
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
            HttpSession session= req.getSession();
            IFlightDao flightDao = new FlightDao();
            String from = req.getParameter(ConstantsJSP.FROM);
            String to = req.getParameter(ConstantsJSP.TO);
            String date = req.getParameter(ConstantsJSP.DATE);
            List<Flight> flights;
            if("".equals(date)){
                flights = flightDao.findByParameters(from, to);
            }
            else{
                flights = flightDao.findByAllParameters(from, to, date);
            }
            if (!flights.isEmpty()) {
                session.setAttribute(ConstantsJSP.FLIGHTS, flights);
            } else {
                logger.warn("Nothing found!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, "Nothing found!");
            }
            return new CommandResult(ConstantsJSP.HOME_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new CommandResult(ConstantsJSP.ERROR_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}
