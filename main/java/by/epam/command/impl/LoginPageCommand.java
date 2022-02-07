package by.epam.command.impl;

import by.epam.command.ICommand;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return ConstantsJSP.LOGIN_PAGE_URL;
    }
}
