package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.command.transmission.RedirectCommandResult;
import by.epam.dao.IUserDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.service.UserService;
import by.epam.utils.ConstantsJSP;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfileCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(UpdateProfileCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            String passwordOld = req.getParameter(ConstantsJSP.PASSWORD_OLD_NAME);
            String passwordConfirm = req.getParameter(ConstantsJSP.PASSWORD_CONFIRM_NAME);
            if (UserService.isValidForUpdate(session, password, passwordOld, passwordConfirm)) {
                UserService.updateUser(session, password);
                return new RedirectCommandResult(ConstantsJSP.AIRCOST_URL + ConstantsJSP.PROFILE_PAGE_URL);
            } else {
                logger.warn("Password entered incorrectly!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.UPDATE_PAGE_URL);
            }
        } catch (ServiceException e) {
            logger.error("Unable to update the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.SECOND_ERROR_CODE);
            return new ForwardCommandResult(ConstantsJSP.UPDATE_PAGE_URL);
        }
    }
}
