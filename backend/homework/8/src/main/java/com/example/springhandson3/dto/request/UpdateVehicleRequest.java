package com.example.springhandson3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This is the DTO class for handling update vehicle request
 */
@AllArgsConstructor
@Data
public class UpdateVehicleRequest {
    private long id;
    private String name;
    private String color;
    private double price;
}
