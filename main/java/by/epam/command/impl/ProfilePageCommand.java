package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfilePageCommand implements ICommand {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
    }
}
