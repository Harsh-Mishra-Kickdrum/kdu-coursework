package com.example.springhandson3.services;

import com.example.springhandson3.dto.request.AddVehicleRequest;
import com.example.springhandson3.dto.request.UpdateVehicleRequest;
import com.example.springhandson3.dto.response.VehicleResponse;
import com.example.springhandson3.entity.Vehicle;
import com.example.springhandson3.exception.customexceptions.BadRequestException;
import com.example.springhandson3.exception.customexceptions.ResourceNotFoundException;
import com.example.springhandson3.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * We are using Spring's @ControllerAdvice to globally handle exceptions.
 * In this scenario, it's generally not necessary to explicitly call the
 * handlers in our service methods. Instead, we should let the exceptions
 * propagate up to the controller layer, where they will be automatically
 * caught and handled  by our GlobalExceptionHandler.

 * In this approach, when an exception is thrown in the service layer,
 * it will bubble up to the controller layer, where the GlobalExceptionHandler
 * will catch it and delegate to the appropriate handler (ResourceNotFoundExceptionHandler
 * or BadRequestExceptionHandler).
 * This way, the exception handling is centralized and consistent across our application.
 *
 */

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    static final String VEHICLEPRINTSTRING = "Vehicle with ID ";

    private final VehicleRepository vehicleRepository;
    public static final Logger logger = LoggerFactory.getLogger(VehicleServiceImp.class);

    /**
     * Adds a new vehicle to the repository.
     * @param request The vehicle data to add.
     * @return The response containing the added vehicle data.
     * @throws BadRequestException if the price of the vehicle is negative.
     */
    @Override
    public VehicleResponse addVehicle(AddVehicleRequest request) {
        if (request.getPrice() < 0) {
            logger.error("Invalid price in add vehicle request");
            throw new BadRequestException("Price cannot be negative");
        }

        Vehicle savedVehicle = vehicleRepository.save(convertToEntity(request));
        return convertToResponse(savedVehicle);
    }

    /**
     * Retrieves a vehicle by its ID.
     * @param id The ID of the vehicle to retrieve.
     * @return The response containing the vehicle data.
     * @throws ResourceNotFoundException if no vehicle with the specified ID is found.
     */
    @Override
    public VehicleResponse getVehicle(long id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new ResourceNotFoundException(VEHICLEPRINTSTRING + id + " not found");
        }
        return convertToResponse(vehicle);
    }

    /**
     * Updates the data of an existing vehicle.
     * @param id The ID of the vehicle to update.
     * @param request The new data for the vehicle.
     * @return The response containing the updated vehicle data.
     * @throws ResourceNotFoundException if no vehicle with the specified ID is found.
     */
    @Override
    public VehicleResponse updateVehicle(long id, UpdateVehicleRequest request) {
        Vehicle existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle == null) {
            throw new ResourceNotFoundException(VEHICLEPRINTSTRING + id + " not found for update");
        }
        BeanUtils.copyProperties(request, existingVehicle, "id");
        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return convertToResponse(updatedVehicle);
    }

    /**
     * Deletes a vehicle from the repository.
     * @param id The ID of the vehicle to delete.
     * @throws ResourceNotFoundException if no vehicle with the specified ID is found.
     */
    @Override
    public void deleteVehicle(long id) {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new ResourceNotFoundException(VEHICLEPRINTSTRING + id + " not found for deletion");
        }
        vehicleRepository.deleteById(id);
    }

    /**
     * Retrieves all vehicles in the repository.
     * @return A list of responses containing all vehicle data.
     */
    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    /**
     * Retrieves the most expensive vehicle in the repository.
     * @return The most expensive vehicle.
     */
    @Override
    public Vehicle getMostExpensive() {
        return vehicleRepository.findMostExpensive();
    }

    /**
     * Retrieves the least expensive vehicle in the repository.
     * @return The least expensive vehicle.
     */
    @Override
    public Vehicle getLeastExpensive() {
        return vehicleRepository.findLeastExpensive();
    }

    // method for mapping response to entity
    private Vehicle convertToEntity(AddVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(request, vehicle);
        return vehicle;
    }

    // method for mapping entity to response

    private VehicleResponse convertToResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        BeanUtils.copyProperties(vehicle, response);
        return response;
    }
}

