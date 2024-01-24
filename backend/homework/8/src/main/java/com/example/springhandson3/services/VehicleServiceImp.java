package com.example.springhandson3.services;

import com.example.springhandson3.dto.request.AddVehicleRequest;
import com.example.springhandson3.dto.request.UpdateVehicleRequest;
import com.example.springhandson3.dto.response.VehicleResponse;
import com.example.springhandson3.entity.Vehicle;
import com.example.springhandson3.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Nullable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public static final Logger logger
            = LoggerFactory.getLogger(VehicleServiceImp.class);

    @Override
    public VehicleResponse addVehicle(AddVehicleRequest request) {
        Vehicle savedVehicle = vehicleRepository.save(convertToEntity(request));
        return convertToResponse(savedVehicle);
    }

    @Override
    public VehicleResponse getVehicle(long id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            logger.warn("Vehicle not found");
        }
        return convertToResponse(vehicle);
    }

    @Override
    public VehicleResponse updateVehicle(long id, UpdateVehicleRequest request) {
        Vehicle existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle == null) {
            logger.warn("Vehicle not found");
        }

        BeanUtils.copyProperties(request, existingVehicle, "id");

        @Nullable Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return convertToResponse(updatedVehicle);
    }

    @Override
    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }


    private Vehicle convertToEntity(AddVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(request, vehicle);
        return vehicle;
    }

    private VehicleResponse convertToResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        BeanUtils.copyProperties(vehicle, response);
        return response;
    }

    @Override
    public Vehicle getMostExpensive() {
        return vehicleRepository.findMostExpensive();
    }

    @Override
    public Vehicle getLeastExpensive() {
        return vehicleRepository.findLeastExpensive();
    }
}
