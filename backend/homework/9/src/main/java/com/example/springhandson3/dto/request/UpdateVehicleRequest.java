package com.example.springhandson3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for handling update vehicle requests.
 * It encapsulates the data needed to update an existing vehicle in the system.
 */
@Data // Generates getters, setters, equals, hashCode, and toString methods.
@AllArgsConstructor // Generates a constructor with all arguments.
public class UpdateVehicleRequest {

    /**
     * The ID of the vehicle to update.
     */
    private long id;

    /**
     * The new or updated name of the vehicle.
     */
    private String name;

    /**
     * The new or updated color of the vehicle.
     */
    private String color;

    /**
     * The new or updated price of the vehicle.
     */
    private double price;
}
