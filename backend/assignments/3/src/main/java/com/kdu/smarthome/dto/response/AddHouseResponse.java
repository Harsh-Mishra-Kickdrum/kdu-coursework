package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.HouseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents the response body for adding a house to the system.
 * It includes the operation's outcome message, the house object, and the HTTP status.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHouseResponse {

    /**
     * The message indicating the result of the add house operation.
     * It provides feedback such as success or failure reasons.
     */
    @NotBlank(message = "Message Field should not be empty")
    private String message;

    /**
     * The house object that was added or attempted to be added.
     * It contains detailed information about the house.
     */
    @NotBlank(message = "House Entity  field should not be empty")
    private HouseEntity house;


    /**
     * The HTTP status code resulting from the add house operation.
     * It helps in understanding the outcome (e.g., success, error) at the HTTP level.
     */
    @NotBlank(message = "HTTP Status field should not be empty")
    private HttpStatus httpStatus;

}
