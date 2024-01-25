package com.example.springhandson3.dto.response;

import lombok.Data;

/**
 * DTO class for encapsulating vehicle data sent in responses.
 * It includes basic vehicle attributes to be shared with clients.
 */
@Data // Generates getters, setters, equals, hashCode, and toString methods.
public class VehicleResponse {

    /**
     * The unique identifier of the vehicle.
     */
    private long id;

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
