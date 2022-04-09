package by.epam.command.transmission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectCommandResult extends CommandResult {

    public RedirectCommandResult(String page) {
        super(page);
    }

    @Override
    public void transmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.sendRedirect(super.getPage());
    }
}
