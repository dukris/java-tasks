package by.epam.controller;

import by.epam.dao.UserDao;
import by.epam.entity.ConstantsJSP;
import by.epam.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class RegisterController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegisterController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.REG_PAGE_URL);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String name = req.getParameter(ConstantsJSP.USER_NAME);
        String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
        String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
        User user = new User((int) (Math.random() * 50), name, email, password);
        if (dao.create(user)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
            rd.forward(req, resp);
        } else {
            logger.warning("This email is already taken!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "This email is already taken! Try again!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.REG_PAGE_URL);
            rd.forward(req, resp);
        }
    }
}
