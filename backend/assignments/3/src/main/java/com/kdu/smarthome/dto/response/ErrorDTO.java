package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ErrorDTO is a data transfer object used to send error information back to the client.
 * It encapsulates details about the error including a message, a description, and an HTTP status code.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ErrorDTO {

    private String message;
    private String description;
    private int httpStatus;

    /**
     * Constructor for ErrorDTO when only message and httpStatus are provided.
     * This constructor logs the creation of an ErrorDTO with these details.
     *
     * @param message    the error message
     * @param httpStatus the HTTP status code associated with the error
     */
    public ErrorDTO(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        log.error("ErrorDTO created with message: {} and HTTP status: {}", message, httpStatus);
    }
}
