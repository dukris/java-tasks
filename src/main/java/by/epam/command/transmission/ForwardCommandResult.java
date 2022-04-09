package by.epam.command.transmission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardCommandResult extends CommandResult {


    public ForwardCommandResult(String page) {
        super(page);
    }

    @Override
    public void transmit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(super.getPage()).forward(req, resp);
    }
}
