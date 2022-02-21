package by.epam.command;

import by.epam.command.impl.*;

public enum CommandType {
    LOGIN_PAGE(new LoginPageCommand()), //Java8 ??????????????
    REGISTER_PAGE(new RegisterPageCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    HOME_PAGE(new HomePageCommand()),
    HOME(new HomeCommand()),
    ERROR(new ErrorPageCommand()),
    UPDATE_PROFILE_PAGE(new UpdateProfilePageCommand()),
    LOGOUT_PAGE(new LogoutPageCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE_PAGE(new ProfilePageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    ADD_PAGE(new AddFlightPageCommand()),
    ADD(new AddFlightCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand());

    private ICommand command;

    CommandType(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
