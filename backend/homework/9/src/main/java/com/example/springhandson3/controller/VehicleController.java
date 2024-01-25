package com.example.springhandson3.controller;

import com.example.springhandson3.dto.request.AddVehicleRequest;
import com.example.springhandson3.dto.request.UpdateVehicleRequest;
import com.example.springhandson3.dto.response.VehicleResponse;
import com.example.springhandson3.entity.Vehicle;
import com.example.springhandson3.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    /**
     * Adds a new vehicle.
     *
     * @param request the vehicle to add.
     * @return ResponseEntity containing the added vehicle and HTTP status.
     */
    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody AddVehicleRequest request) {
        logger.info("Adding a new vehicle");
        VehicleResponse response = vehicleService.addVehicle(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id the ID of the vehicle.
     * @return ResponseEntity containing the found vehicle and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable long id) {
        logger.info("Retrieving vehicle with ID {}", id);
        VehicleResponse response = vehicleService.getVehicle(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates a vehicle.
     *
     * @param id      the ID of the vehicle to update.
     * @param request the new vehicle data.
     * @return ResponseEntity containing the updated vehicle and HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable long id, @RequestBody UpdateVehicleRequest request) {
        logger.info("Updating vehicle with ID {}", id);
        VehicleResponse response = vehicleService.updateVehicle(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a vehicle by its ID.
     *
     * @param id the ID of the vehicle.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable long id) {
        logger.info("Deleting vehicle with ID {}", id);
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all vehicles.
     *
     * @return ResponseEntity containing the list of all vehicles and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        logger.info("Retrieving all vehicles");
        List<VehicleResponse> response = vehicleService.getAllVehicles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves the most expensive vehicle.
     *
     * @return ResponseEntity containing the most expensive vehicle and HTTP status.
     */
    @GetMapping("/most-expensive")
    public ResponseEntity<Vehicle> getMostExpensive() {
        logger.info("Retrieving the most expensive vehicle");
        Vehicle mostExpensive = vehicleService.getMostExpensive();
        return ResponseEntity.ok(mostExpensive);
    }

    /**
     * Retrieves the least expensive vehicle.
     *
     * @return ResponseEntity containing the least expensive vehicle and HTTP status.
     */
    @GetMapping("/least-expensive")
    public ResponseEntity<Vehicle> getLeastExpensive() {
        logger.info("Retrieving the least expensive vehicle");
        Vehicle leastExpensive = vehicleService.getLeastExpensive();
        return ResponseEntity.ok(leastExpensive);
    }
}
