package by.epam.command.implementation.page;

import by.epam.command.transmission.CommandResult;
import by.epam.command.transmission.ForwardCommandResult;
import by.epam.command.ICommand;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfilePageCommand implements ICommand {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return new ForwardCommandResult(ConstantsJSP.UPDATE_PAGE_URL);
    }
}

