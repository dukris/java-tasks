package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.impl.IUserDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.logging.Logger;

public class LoginCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            IUserDao userDao = new UserDao();
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent() && user.get().getPassword().equals(password)) {
                return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
            } else {
                logger.warning("Invalid Username or Password!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, "Invalid Username or Password!");
                return new CommandResult(ConstantsJSP.LOGIN_PAGE_URL, CommandResultType.FORWARD);
            }
        } catch (DaoException e) {
            logger.warning(e.getMessage());
            return new CommandResult(ConstantsJSP.ERROR_PAGE_URL, CommandResultType.FORWARD);
        }

    }
}
