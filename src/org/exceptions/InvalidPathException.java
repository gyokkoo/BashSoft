package org.exceptions;

public class InvalidPathException extends RuntimeException {

    private static final String INVALID_PATH = "Invalid path! The source does not exist.";

    public InvalidPathException() {
        super(INVALID_PATH);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
