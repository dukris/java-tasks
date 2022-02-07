package by.epam.command.impl;

import by.epam.command.ICommand;
import by.epam.dao.IDao;
import by.epam.dao.UserDao;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class RegisterCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        IDao<User> userDao = new UserDao();
        String name = req.getParameter(ConstantsJSP.USER_NAME);
        String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
        String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
        User user = new User(name, email, password);
        try {
            userDao.create(user);
        } catch (DaoException e) {
            logger.warning("Unable to register the user!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "This email is already taken! Try again!");
            return ConstantsJSP.REGISTER_PAGE_URL;
        }
        return ConstantsJSP.PROFILE_PAGE_URL;
    }
}
