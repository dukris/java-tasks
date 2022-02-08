package by.epam.command;

import by.epam.command.impl.*;

public enum CommandType {
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTER_PAGE(new RegisterPageCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    HOME(new HomePageCommand()),
    ERROR(new ErrorPageCommand());

    private ICommand command;

    CommandType(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
