package by.epam.command.implementation;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ErrorCommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.entity.*;
import by.epam.exceptions.ServiceException;
import by.epam.service.OrderService;
import by.epam.service.UserService;
import by.epam.utils.ConstantsJSP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
            String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
            if("".equals(email) || "".equals(password)){
                logger.warn("Fields are empty!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.THIRD_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.LOGIN_PAGE_URL);
            }
            Optional<User> user = UserService.findUser(email);
            if (UserService.isValidForLogin(user, password)) {
                OrderService.setOrderInfoByUserId(session, user.get());
                UserService.setCities(session);
                if (user.get().getRoleType() == RoleType.ADMIN) {
                    UserService.setAdminAttributes(session);
                }
                return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
            } else {
                logger.warn("Invalid Username or Password!");
                req.setAttribute(ConstantsJSP.ERROR_NAME, ConstantsJSP.FIRST_ERROR_CODE);
                return new ForwardCommandResult(ConstantsJSP.LOGIN_PAGE_URL);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return new ErrorCommandResult();
        }
    }
}
