package by.epam.exceptions;

public class FrontControllerException extends RuntimeException{
    public FrontControllerException() {
        super();
    }

    public FrontControllerException(String message) {
        super(message);
    }
}
