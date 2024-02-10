package com.kdu.smarthome.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents the response for adding an inventory item to the smart home system.
 * It encapsulates the response message, an object reference related to the operation,
 * and the HTTP status code to indicate the result of the operation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInventoryResponse {

    /**
     * The message indicating the result of the add inventory operation.
     * It provides feedback such as success or failure reasons.
     */
    @NotBlank(message = "Message field should not be empty")
    private String message;

    /**
     * An object reference related to the inventory addition operation.
     * This could be an identifier or key for the added inventory item.
     */
    @NotBlank(message = "Object  field should not be empty")
    private String object;

    /**
     * The HTTP status code resulting from the add inventory operation.
     * It helps in understanding the outcome (e.g., success, error) at the HTTP level.
     */
    @NotBlank(message = "HTTP Status field should not be empty")
    private HttpStatus httpStatus;
}
