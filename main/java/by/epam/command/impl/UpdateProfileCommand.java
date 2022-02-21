package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.impl.IUserDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
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
            IUserDao userDao = new UserDao();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            String passwordOld = req.getParameter(ConstantsJSP.PASSWORD_OLD_NAME);
            String passwordConfirm = req.getParameter(ConstantsJSP.PASSWORD_CONFIRM_NAME);
            if (DigestUtils.sha256Hex(passwordOld).equals(userDao.getEncodedPassword(user.getEmail())) && password.equals(passwordConfirm)) {
                user.setPassword(DigestUtils.sha256Hex(password));
                userDao.update(user);
                session.setAttribute(ConstantsJSP.USER, user);
                return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
            } else {
                logger.warn("Password entered incorrectly!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, "Password entered incorrectly!");
                return new CommandResult(ConstantsJSP.UPDATE_PAGE_URL, CommandResultType.FORWARD);
            }
        } catch (DaoException e) {
            logger.error("Unable to update the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "This email is already taken! Try again!");
            return new CommandResult(ConstantsJSP.UPDATE_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}
