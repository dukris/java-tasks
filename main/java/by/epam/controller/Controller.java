package by.epam.controller;

import by.epam.command.CommandFactory;
import by.epam.command.CommandResult;
import by.epam.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ICommand iCommand = CommandFactory.getCommand(req);
        CommandResult commandResult = iCommand.execute(req, resp);
        transmit(commandResult, req, resp);
    }

    private void transmit(CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (commandResult.isRedirect()) {
            resp.sendRedirect(commandResult.getPage());
        } else {
            req.getRequestDispatcher(commandResult.getPage()).forward(req, resp);
        }
    }
}
