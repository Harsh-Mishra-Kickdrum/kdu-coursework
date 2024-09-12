package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to update the address of a house.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {

    @NotBlank(message = "newAddress field should not be empty")
    private String newAddress;
}
