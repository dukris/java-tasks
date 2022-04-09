package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ErrorCommandResult;
import by.epam.command.ICommand;;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.entity.User;
import by.epam.exceptions.ServiceException;
import by.epam.service.FlightService;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReserveFlightCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(ReserveFlightCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            if (user == null) {
                session.removeAttribute(ConstantsJSP.FLIGHTS);
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.SECOND_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.LOGIN_PAGE_URL);
            } else {
                long id = Long.parseLong(req.getParameter(ConstantsJSP.ID));
                FlightService.reserveFlight(session, id);
                return new ForwardCommandResult(ConstantsJSP.RESERVE_PAGE_URL);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return new ErrorCommandResult();
        }
    }
}

