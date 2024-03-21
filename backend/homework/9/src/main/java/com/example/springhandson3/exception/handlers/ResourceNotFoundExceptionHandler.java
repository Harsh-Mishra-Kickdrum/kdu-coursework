package com.example.springhandson3.exception.handlers;

import com.example.springhandson3.exception.ErrorResponse;
import com.example.springhandson3.exception.customexceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Handler class for ResourceNotFoundException.
 * This class is responsible for converting ResourceNotFoundException instances
 * into a standardized ErrorResponse format that can be returned to the client.
 */
@Component
public class ResourceNotFoundExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundExceptionHandler.class);

    /**
     * Handles the ResourceNotFoundException and transforms it into an ErrorResponse.
     * Logs the exception details for monitoring and debugging purposes.
     *
     * @param ex The ResourceNotFoundException instance to be handled.
     * @return ErrorResponse containing the status code and error message.
     */
    public ErrorResponse handleException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
