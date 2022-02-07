package by.epam.controller;

import by.epam.command.CommandFactory;
import by.epam.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = process(req, resp);
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = process(req, resp);
        resp.sendRedirect(redirect);
    }

    private String process(HttpServletRequest req, HttpServletResponse resp) {
        ICommand iCommand = CommandFactory.getCommand(req);
        return iCommand.execute(req, resp);
    }
}
