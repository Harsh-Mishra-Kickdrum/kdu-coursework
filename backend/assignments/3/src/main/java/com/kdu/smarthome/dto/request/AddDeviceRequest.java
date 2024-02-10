package com.kdu.smarthome.dto.request;


import lombok.Builder;
import lombok.Data;

/**
 * Represents a request to add a device to a specific room within a house.
 * This class is used to capture the necessary information required to add a device,
 * including identifying the house, the room within the house, and the device details.
 */
@Data
@Builder
public class AddDeviceRequest {

    // Unique identifier for the house where the device is to be added.
    private String houseId;

    // Unique identifier for the room within the house where the device is to be added.
    private String roomId;

    // Unique identifier (Kickston ID) for the device being added.
    private String kickstonId;
}
