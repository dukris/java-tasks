package by.epam.command;

import by.epam.command.transmission.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
