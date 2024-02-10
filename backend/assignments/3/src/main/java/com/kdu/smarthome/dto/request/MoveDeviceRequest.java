package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to move a device to a different room within the same house.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveDeviceRequest {

    @NotBlank(message = "deviceId field should not be empty")
    private String deviceId;

    @NotBlank(message = "roomId field should not be empty")
    private String roomId;
}
