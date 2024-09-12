package com.kdu.smarthome.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents a response containing all the details for rooms and devices.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAllDetailsResponse {

    /**
     * A message providing information about the response.
     */
    @NotBlank(message = "Message is required")
    private String message;

    /**
     * A string representation of rooms and devices details.
     */
    @NotBlank(message = "Rooms and devices information is required")
    private String roomsAndDevices;

    /**
     * The HTTP status of the response.
     */
    @NotBlank(message = "HTTP status is required")
    private HttpStatus httpStatus;
}
