package by.epam.command.transmission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class CommandResult {
    private final String page;

    public CommandResult(String page) {
        this.page = page;
    }

    public abstract void transmit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    public String getPage() {
        return page;
    }
}
