package by.epam.command;

import by.epam.utils.ConstantsJSP;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public final class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());

    public static ICommand getCommand(HttpServletRequest req) {
        ICommand icommand;
        String command = req.getParameter(ConstantsJSP.COMMAND_NAME);
        if (command != null) {
            try {
                icommand = CommandType.valueOf(command.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                logger.warning("Incorrect command!");
                icommand = CommandType.ERROR.getCommand();
            }
        } else {
            logger.warning("Command is empty!");
            icommand = CommandType.ERROR.getCommand();
        }
        return icommand;
    }
}
