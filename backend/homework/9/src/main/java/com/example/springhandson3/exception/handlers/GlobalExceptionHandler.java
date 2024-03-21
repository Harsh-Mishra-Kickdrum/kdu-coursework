package com.example.springhandson3.exception.handlers;

import com.example.springhandson3.exception.ErrorResponse;
import com.example.springhandson3.exception.customexceptions.ResourceNotFoundException;
import com.example.springhandson3.exception.customexceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for the application.
 * This class intercepts exceptions thrown across the whole application
 * and delegates their handling to specific handler components.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ResourceNotFoundExceptionHandler resourceNotFoundExceptionHandler;
    private final BadRequestExceptionHandler badRequestExceptionHandler;

    @Autowired
    public GlobalExceptionHandler(ResourceNotFoundExceptionHandler resourceNotFoundExceptionHandler,
                                  BadRequestExceptionHandler badRequestExceptionHandler) {
        this.resourceNotFoundExceptionHandler = resourceNotFoundExceptionHandler;
        this.badRequestExceptionHandler = badRequestExceptionHandler;
    }

    /**
     * Handles ResourceNotFoundExceptions by delegating to a specific handler.
     * Sets the HTTP response status to NOT_FOUND (404).
     *
     * @param ex The caught ResourceNotFoundException.
     * @return An ErrorResponse object.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return resourceNotFoundExceptionHandler.handleException(ex);
    }

    /**
     * Handles BadRequestExceptions by delegating to a specific handler.
     * Sets the HTTP response status to BAD_REQUEST (400).
     *
     * @param ex The caught BadRequestException.
     * @return An ErrorResponse object.
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequest(BadRequestException ex) {
        return badRequestExceptionHandler.handleException(ex);
    }
}
