package com.kdu.smarthome.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents the response for an address update operation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressResponse {

    /**
     * A message providing details about the outcome of the address update operation.
     */
    @NotBlank(message = "A descriptive message is required")
    private String message;

    /**
     * A representation of the updated address object.
     */
    @NotBlank(message = "Updated address information is required")
    private String addressInfo;

    /**
     * The HTTP status code of the response.
     */
    @NotBlank(message = "HTTP status indicating the outcome is required")
    private HttpStatus httpStatus;
}
