package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.IDao;
import by.epam.dao.impl.IUserDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
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
            IUserDao userDao = new UserDao();
            String name = req.getParameter(ConstantsJSP.USER_NAME);
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            User user = new User(name, email, password);
            userDao.create(user);
            session.setAttribute(ConstantsJSP.USER, user);
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error("Unable to register the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "This email is already taken! Try again!");
            return new CommandResult(ConstantsJSP.REGISTER_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}
