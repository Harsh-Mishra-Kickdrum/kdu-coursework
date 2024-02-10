package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to log in to the system, containing necessary credentials.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username field should not be empty")
    private String username;

    @NotBlank(message = "password field should not be empty")
    private String password;
}
