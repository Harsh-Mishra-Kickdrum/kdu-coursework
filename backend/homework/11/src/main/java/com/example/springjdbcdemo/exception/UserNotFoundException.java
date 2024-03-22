package com.example.springjdbcdemo.exception;

/**
 * Custom exception class for handling user-related errors.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor with error message.
     *
     * @param message the error message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
