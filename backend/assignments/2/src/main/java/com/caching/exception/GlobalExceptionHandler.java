package com.caching.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a class annotated with @ControllerAdvice
 * to handle exceptions across the whole application in one global handling component.
 * It intercepts exceptions thrown by methods annotated with @RequestMapping (and similar).
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Logger for this class, used for logging exceptions.
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles exceptions of type CustomException thrown within the application.
     * It logs the exception and returns a ResponseEntity with an appropriate message and HTTP status.
     *
     * @param ex The CustomException instance caught.
     * @return ResponseEntity with the exception message and BAD_REQUEST (HTTP 400) status.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        logger.error("CustomException: {}" , ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions that are not caught by more specific exception handlers.
     * It logs the exception and returns a ResponseEntity with a generic error message and INTERNAL_SERVER_ERROR (HTTP 500) status.
     *
     * @param ex The Exception instance caught.
     * @return ResponseEntity with a generic error message and INTERNAL_SERVER_ERROR (HTTP 500) status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        logger.error("Exception: " + ex.getMessage(), ex);
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
