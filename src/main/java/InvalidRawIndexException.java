package main.java;

public class InvalidRawIndexException extends Exception {
    public InvalidRawIndexException() {
    }

    public InvalidRawIndexException(String message) {
        super(message);
    }

    public InvalidRawIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRawIndexException(Throwable cause) {
        super(cause);
    }

    public InvalidRawIndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
