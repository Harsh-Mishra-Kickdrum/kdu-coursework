package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to add a new inventory item.
 * It captures all necessary details about the device to be added to the inventory.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInventoryRequest {

    @NotBlank(message = "kickstonId field should not be empty")
    private String kickstonId;

    @NotBlank(message = "device_username field should not be empty")
    private String deviceUsername;

    @NotBlank(message = "device_password field should not be empty")
    private String devicePassword;

    @NotBlank(message = "manufacture_date_time field should not be empty")
    private String manufactureDateTime;

    @NotBlank(message = "manufacture_factory_place field should not be empty")
    private String manufactureFactoryPlace;
}
