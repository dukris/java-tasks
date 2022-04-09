package by.epam.command.implementation.page;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ErrorCommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.entity.RoleType;
import by.epam.entity.User;
import by.epam.exceptions.ServiceException;
import by.epam.service.UserService;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomePageCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(HomePageCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
//        try {
            HttpSession session = req.getSession();
            if (session.getAttribute(ConstantsJSP.USER) != null
                    && ((User) session.getAttribute(ConstantsJSP.USER)).getRoleType() == RoleType.ADMIN) {
                return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
            }
//            UserService userService = new UserService();
//            userService.setCities(session);
            return new ForwardCommandResult(ConstantsJSP.HOME_PAGE_URL);
//        } catch (ServiceException e) {
//            logger.error(e.getMessage());
//            return new ErrorCommandResult();
//        }
    }
}
