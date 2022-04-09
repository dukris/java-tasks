package by.epam.command.transmission;

import by.epam.utils.ConstantsJSP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommandResult extends CommandResult{
    public ErrorCommandResult() {
        super(ConstantsJSP.ERROR_PAGE_URL);
    }

    @Override
    public void transmit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(super.getPage()).forward(req, resp);
    }
}
