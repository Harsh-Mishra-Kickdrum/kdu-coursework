package com.example.springsecuritydemo.exception;

/**
 * Custom exception class representing the scenario where a user is not found.
 * This exception extends the RuntimeException, allowing it to be thrown and caught at runtime
 * without the need for explicit declaration in the method's throws clause.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * The message can be retrieved later by the Throwable.getMessage() method.
     *
     * @param message the detail message, which provides specific information about the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
