package com.example.springsecuritydemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ErrorDTO is a Data Transfer Object used for encapsulating error information.
 * It includes details about the error message and status code.
 */
@Data // Lombok annotation to generate getters, setters.
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments.
public class ErrorDTO {
    // Message describing the error
    private String message;

    // HTTP status code associated with the error
    private int statusCode;
}
