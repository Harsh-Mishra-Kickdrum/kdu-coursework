package com.kdu.smarthome.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response for a user registration operation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {

    /**
     * The username of the newly registered user.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * The authentication token issued to the newly registered user.
     */
    @NotBlank(message = "Authentication token is required")
    private String token;
}
