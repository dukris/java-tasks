package servlets;

import beans.ConstantsJSP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int number = Integer.parseInt(req.getParameter(ConstantsJSP.NUMBER_NAME));

            HttpSession session = req.getSession();
            session.setAttribute(ConstantsJSP.NUMBER_NAME, number);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
            rd.forward(req, resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
