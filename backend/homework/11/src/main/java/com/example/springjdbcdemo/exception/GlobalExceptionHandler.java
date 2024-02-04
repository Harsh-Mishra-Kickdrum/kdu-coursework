package com.example.springjdbcdemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for handling exceptions across the entire application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles UserNotFoundException.
     *
     * @param ex the thrown exception
     * @param request the web request during exception
     * @return ResponseEntity with error message and HttpStatus NOT_FOUND
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundExceptionHandler(UserNotFoundException ex, WebRequest request) {
        logger.error("UserNotFoundException: ", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DataMappingException.
     *
     * @param ex the thrown exception
     * @param request the web request during exception
     * @return ResponseEntity with error message and HttpStatus BAD_REQUEST
     */
    @ExceptionHandler(DataMappingException.class)
    public ResponseEntity<Object> dataMappingExceptionHandler(DataMappingException ex, WebRequest request) {
        logger.error("DataMappingException: ", ex);
        // You might choose a different HttpStatus based on your application's needs
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic Exception.
     *
     * @param ex the thrown exception
     * @param request the web request during exception
     * @return ResponseEntity with error message and HttpStatus INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error("Exception: ", ex);
        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
