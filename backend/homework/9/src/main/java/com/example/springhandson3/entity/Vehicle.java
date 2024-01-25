package com.example.springhandson3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a vehicle.
 * This class is used for data storage and retrieval.
 */
@Data // Generates getters, setters, equals, hashCode, and toString methods.
@NoArgsConstructor // Generates a no-argument constructor.
@AllArgsConstructor // Generates a constructor with all arguments.
public class Vehicle {

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
