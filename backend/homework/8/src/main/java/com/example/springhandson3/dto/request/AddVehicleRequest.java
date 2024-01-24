package com.example.springhandson3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This is a DTO class for handling add vehicle request
 */
@AllArgsConstructor
@Data
public class AddVehicleRequest {
    private String name;
    private String color;
    private double price;
}
