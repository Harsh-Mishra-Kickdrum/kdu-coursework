package com.caching.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CustomException is a specialized RuntimeException that represents
 * exceptions specific to the Geocoding and Decoding application.
 * This class extends RuntimeException and is used to throw custom exceptions
 * throughout the application with meaningful messages.
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Logger for this class, used to log error messages when an exception is thrown.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomException.class);

    /**
     * Constructs a new CustomException with the specified detail message.
     * The message is logged as an error using the class logger.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public CustomException(String message) {
        super(message);
        logger.error(message);
    }
}
