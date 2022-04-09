package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.command.transmission.RedirectCommandResult;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.entity.Flight;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.service.FlightService;
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
            String from = req.getParameter(ConstantsJSP.FROM);
            String to = req.getParameter(ConstantsJSP.TO);
            String date = req.getParameter(ConstantsJSP.DATE);
            int amount = Integer.parseInt(req.getParameter(ConstantsJSP.AMOUNT));
            int price = Integer.parseInt(req.getParameter(ConstantsJSP.PRICE));
            FlightService.updateFlight(session, from, to, date, amount, price);
            return new RedirectCommandResult(ConstantsJSP.AIRCOST_URL + ConstantsJSP.PROFILE_PAGE_URL);
        } catch (ServiceException e) {
            logger.error("Unable to edit the flight!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
            return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
        }
    }
}

