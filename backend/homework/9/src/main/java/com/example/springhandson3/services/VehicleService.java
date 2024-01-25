package com.example.springhandson3.services;

import com.example.springhandson3.dto.request.AddVehicleRequest;
import com.example.springhandson3.dto.request.UpdateVehicleRequest;
import com.example.springhandson3.dto.response.VehicleResponse;
import com.example.springhandson3.entity.Vehicle;

import java.util.List;

/**
 * This is the interface for vehicle services
 */
public interface VehicleService {

    VehicleResponse addVehicle(AddVehicleRequest request);

    VehicleResponse getVehicle(long id);

    VehicleResponse updateVehicle(long id, UpdateVehicleRequest request);

    void deleteVehicle(long id);

    List<VehicleResponse> getAllVehicles();


    Vehicle getMostExpensive();

    Vehicle getLeastExpensive();
}
