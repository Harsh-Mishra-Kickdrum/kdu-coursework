package com.example.springhandson3.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a standardized format for error responses in the application.
 * This class is used to provide a consistent structure for conveying error
 * information to clients.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status; // HTTP status code
    private String message; // Detailed error message
}
