package com.kdu.smarthome.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for device registration process.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRegisterResponse {

    /**
     * Message indicating the outcome of the registration process.
     */
    @NotBlank(message = "Registration message cannot be empty")
    private String message;

    /**
     * Object associated with the registration, typically device information.
     */
    @NotBlank(message = "Device information cannot be empty")
    private String deviceInfo;

    /**
     * HTTP status indicating the result of the device registration request.
     */
    @NotBlank(message = "HTTP status must be provided")
    private HttpStatus httpStatus;
}
