package com.example.springhandson3.exception.customexceptions;

/**
 * Custom exception class for handling bad request scenarios.
 * This exception is typically thrown when client input is invalid or inappropriate.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructs a new BadRequestException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method).
     */
    public BadRequestException(String message) {
        super(message);
    }
}
