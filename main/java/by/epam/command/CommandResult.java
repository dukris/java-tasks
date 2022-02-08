package by.epam.command;

public class CommandResult {
    private final String page;
    private final CommandResultType commandResultType;

    public CommandResult(String page, CommandResultType commandResultType) {
        this.page = page;
        this.commandResultType = commandResultType;
    }

    public boolean isRedirect() {
        return commandResultType == CommandResultType.REDIRECT;
    }

    public boolean isForward() {
        return commandResultType == CommandResultType.FORWARD;
    }

    public String getPage() {
        return page;
    }
}
