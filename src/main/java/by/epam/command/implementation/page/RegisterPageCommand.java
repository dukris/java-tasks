package by.epam.command.implementation.page;

import by.epam.command.transmission.CommandResult;
import by.epam.command.ICommand;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.utils.ConstantsJSP;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterPageCommand implements ICommand {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (session.getAttribute(ConstantsJSP.USER) != null) {
            return new ForwardCommandResult(ConstantsJSP.PROFILE_PAGE_URL);
        } else {
            return new ForwardCommandResult(ConstantsJSP.REGISTER_PAGE_URL);
        }
    }
}
