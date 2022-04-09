package by.epam.filter;

import by.epam.command.CommandType;
import by.epam.command.ICommand;
import by.epam.command.factory.CommandFactory;
import by.epam.command.transmission.ErrorCommandResult;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(CommandFilter.class.getName());
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig){
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String command = httpRequest.getParameter(ConstantsJSP.COMMAND_NAME);
        if (!"".equals(command)) {
            try {
                CommandType.valueOf(command.toUpperCase());
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IllegalArgumentException | NullPointerException e) {
                logger.error("Incorrect command!");
                new ErrorCommandResult().transmit(httpRequest, httpResponse);
            }
        } else {
            logger.error("Command is empty!");
            new ErrorCommandResult().transmit(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
