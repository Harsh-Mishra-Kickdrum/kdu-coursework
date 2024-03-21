package com.example.springhandson3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for handling add vehicle request.
 * It encapsulates the data needed to create a new vehicle in the system.
 */
@Data // Generates getters, setters, equals, hashCode, and toString methods.
@AllArgsConstructor // Generates a constructor with all arguments.
public class AddVehicleRequest {
    /**
     * The name of the vehicle.
     */
    private String name;

    /**
     * The color of the vehicle.
     */
    private String color;

    /**
     * The price of the vehicle.
     */
    private double price;
}
