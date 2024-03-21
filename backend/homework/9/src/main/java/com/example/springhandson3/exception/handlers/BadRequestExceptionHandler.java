package com.example.springhandson3.exception.handlers;

import com.example.springhandson3.exception.ErrorResponse;
import com.example.springhandson3.exception.customexceptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Handler class for BadRequestException.
 * This class is responsible for converting BadRequestException instances
 * into a standardized ErrorResponse format that can be returned to the client.
 */
@Component
public class BadRequestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

    /**
     * Handles the BadRequestException and transforms it into an ErrorResponse.
     * Logs the exception details for monitoring and debugging purposes.
     *
     * @param ex The BadRequestException instance to be handled.
     * @return ErrorResponse containing the status code and error message.
     */
    public ErrorResponse handleException(BadRequestException ex) {
        logger.error("Bad request: {}", ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
