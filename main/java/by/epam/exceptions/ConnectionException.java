package by.epam.exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {

    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }
}
