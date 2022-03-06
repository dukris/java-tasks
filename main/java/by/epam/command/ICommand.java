package by.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
