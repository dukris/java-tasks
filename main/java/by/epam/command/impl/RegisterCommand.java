package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.IDao;
import by.epam.dao.impl.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class RegisterCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            IDao<User> userDao = new UserDao();
            String name = req.getParameter(ConstantsJSP.USER_NAME);
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            User user = new User(name, email, password);
            userDao.create(user);
        } catch (DaoException e) {
            logger.warning("Unable to register the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "This email is already taken! Try again!");
            return new CommandResult(ConstantsJSP.REGISTER_PAGE_URL, CommandResultType.FORWARD);
        }
        return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
    }
}
