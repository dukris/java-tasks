package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.command.transmission.RedirectCommandResult;
import by.epam.exceptions.ServiceException;
import by.epam.service.UserService;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String name = req.getParameter(ConstantsJSP.USER_NAME);
            String surname = req.getParameter(ConstantsJSP.SURNAME_NAME);
            String passport = req.getParameter(ConstantsJSP.PASSPORT_NAME);
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            if("".equals(email) || "".equals(password) || " ".equals(surname) || " ".equals(name) || " ".equals(passport) || password.length()<8){
                logger.warn("Fields are empty!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.SECOND_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.REGISTER_PAGE_URL);
            }
            UserService.registerUser(session, name, surname, passport, email, password);
            return new RedirectCommandResult(ConstantsJSP.AIRCOST_URL + ConstantsJSP.PROFILE_PAGE_URL);
        } catch (ServiceException e) {
            logger.error("Unable to register the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
            return new ForwardCommandResult(ConstantsJSP.REGISTER_PAGE_URL);
        }
    }
}
