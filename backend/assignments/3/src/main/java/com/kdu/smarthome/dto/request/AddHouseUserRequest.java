package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request to add a user to a house.
 * It captures the necessary user identification detail, which is the username.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHouseUserRequest {

    @NotBlank(message = "Username field should not be empty")
    private String username;
}
