package main.java;

public class InvalidColumnLabelException extends Exception {
    public InvalidColumnLabelException() {
    }

    public InvalidColumnLabelException(String message) {
        super(message);
    }

    public InvalidColumnLabelException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidColumnLabelException(Throwable cause) {
        super(cause);
    }

    public InvalidColumnLabelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
