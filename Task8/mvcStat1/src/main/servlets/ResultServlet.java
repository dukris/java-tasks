package servlets;

import beans.ConstantsJSP;
import beans.Operation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strOperation = req.getParameter(ConstantsJSP.OPERATION_NAME);
        Operation operation = Operation.valueOf(strOperation.toUpperCase());

        String[] strNumbers = req.getParameterValues(ConstantsJSP.STAT_NAME);
        double[] numbers = Arrays.stream(strNumbers)
                .mapToDouble(Double::parseDouble)
                .toArray();

        double result = operation.getResult(numbers);

        req.setAttribute(ConstantsJSP.RESULT_NAME, result);
        req.setAttribute(ConstantsJSP.OPERATION_NAME, operation.toString().toLowerCase());
        req.setAttribute(ConstantsJSP.STAT_NAME, numbers);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.RESULT_PAGE_URL);
        rd.forward(req, resp);
    }
}
