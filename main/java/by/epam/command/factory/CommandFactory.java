package by.epam.command.factory;

import by.epam.command.CommandType;
import by.epam.command.ICommand;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public final class CommandFactory {

    private static final Logger logger = LogManager.getLogger(CommandFactory.class.getName());

    public static ICommand getCommand(HttpServletRequest req) {
        ICommand icommand;
        String command = req.getParameter(ConstantsJSP.COMMAND_NAME);
        if (!"".equals(command)) {
            try {
                icommand = CommandType.valueOf(command.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                logger.error("Incorrect command!");
                icommand = CommandType.ERROR.getCommand();
            }
        } else {
            logger.error("Command is empty!");
            icommand = CommandType.ERROR.getCommand();
        }
        return icommand;
    }
}
