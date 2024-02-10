package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response containing information about a successful login.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    /**
     * The username of the user who has successfully logged in.
     */
    private String username;

    /**
     * The authentication token provided upon successful login.
     */
    private String token;
}
