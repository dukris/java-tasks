package by.epam.command.implementation;

import by.epam.command.ICommand;
import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements ICommand {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String sessionLanguage = (String) session.getAttribute(ConstantsJSP.LANGUAGE);
        String requestLanguage = req.getParameter(ConstantsJSP.LANGUAGE);
        if (sessionLanguage == null || requestLanguage == null) {
            session.setAttribute(ConstantsJSP.LANGUAGE, ConstantsJSP.RU);
        }
        if (requestLanguage != null) {
            session.setAttribute(ConstantsJSP.LANGUAGE, requestLanguage);
        }
        System.out.println(session.getAttribute(ConstantsJSP.LANGUAGE));
        return new ForwardCommandResult(ConstantsJSP.START_PAGE_URL);
    }
}
