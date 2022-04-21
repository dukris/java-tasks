package servlets;

import beans.ConstantsJSP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ConstantsJSP.NUMBER_NAME, req.getParameter(ConstantsJSP.NUMBER_NAME));

        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
        rd.forward(req, resp);
    }
}