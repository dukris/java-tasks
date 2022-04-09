package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.command.transmission.RedirectCommandResult;
import by.epam.exceptions.ServiceException;
import by.epam.service.FlightService;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(UpdateFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String deleteChoice = req.getParameter(ConstantsJSP.DELETE_CHOICE);
            String editChoice = req.getParameter(ConstantsJSP.EDIT_CHOICE);
            if (deleteChoice != null) {
                FlightService.deleteFlight(session, deleteChoice);
            }
            if (editChoice != null) {
                FlightService.editFlight(session, editChoice);
                return new RedirectCommandResult(ConstantsJSP.AIRCOST_URL + ConstantsJSP.EDIT_FLIGHT_PAGE_URL);
            }
            return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
        } catch (ServiceException e) {
            logger.error("Unable to delete the flight!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
            return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
        }
    }
}

