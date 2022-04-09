package by.epam.controller;

import by.epam.command.factory.CommandFactory;
import by.epam.command.transmission.CommandResult;
import by.epam.command.ICommand;
import by.epam.dao.connection.impl.ConnectionPool;
import by.epam.exceptions.ConnectionException;
import by.epam.exceptions.FrontControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FrontController.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            logger.error("Servlet wasn't init!");
            throw new FrontControllerException();
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
            super.destroy();
        } catch (ConnectionException e) {
            logger.error("Servlet wasn't destroy!");
            throw new FrontControllerException();
        }
    }

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
        commandResult.transmit(req, resp);
    }
}
