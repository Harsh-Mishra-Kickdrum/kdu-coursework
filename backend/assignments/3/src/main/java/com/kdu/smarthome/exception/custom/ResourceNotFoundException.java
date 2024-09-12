package com.kdu.smarthome.exception.custom;

/**
 * Exception class for handling resource not found scenarios.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor for ResourceNotFoundException.
     *
     * @param message the detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
