package com.example.springhandson3.dto.response;

import lombok.Data;

/**
 * This is the DTO class for handling the response for vehicle attributes
 */
@Data
public class VehicleResponse {
    private long id;
    private String name;
    private String color;
    private double price;
}
