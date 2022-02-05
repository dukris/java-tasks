package by.epam.controller;

import by.epam.dao.UserDao;
import by.epam.entity.ConstantsJSP;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.LOGIN_PAGE_URL);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String email = req.getParameter(ConstantsJSP.EMAIL_NAME);
        String password = req.getParameter(ConstantsJSP.PASSWORD_NAME);
        try{
            User user = dao.findUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
                rd.forward(req, resp);
            }
        }catch (DaoException e){
            logger.warning("Invalid Username or Password!");
            req.setAttribute(ConstantsJSP.ERROR_NAME, "Invalid Username or Password!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.LOGIN_PAGE_URL);
            rd.forward(req, resp);
        }
    }
}