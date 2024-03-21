package com.example.springhandson3.exception.customexceptions;

/**
 * Custom exception class for handling scenarios where a requested resource is not found.
 * This exception is typically thrown when a lookup for a specific entity or resource
 * in the application yields no result.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method).
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
