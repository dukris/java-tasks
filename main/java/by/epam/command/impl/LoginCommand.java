package by.epam.command.impl;

import by.epam.command.ICommand;
import by.epam.dao.IDao;
import by.epam.dao.UserDao;
import by.epam.entity.User;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class LoginCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        IDao<User> userDao = new UserDao();
        String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
        String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
        User user = userDao.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            logger.warning("Invalid Username or Password!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "Invalid Username or Password!");
            return ConstantsJSP.LOGIN_PAGE_URL;
        }
        return ConstantsJSP.PROFILE_PAGE_URL;
    }
}
