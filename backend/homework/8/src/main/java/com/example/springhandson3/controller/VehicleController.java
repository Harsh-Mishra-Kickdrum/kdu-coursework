package com.example.springhandson3.controller;

import com.example.springhandson3.dto.request.AddVehicleRequest;
import com.example.springhandson3.dto.request.UpdateVehicleRequest;
import com.example.springhandson3.dto.response.VehicleResponse;
import com.example.springhandson3.entity.Vehicle;
import com.example.springhandson3.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody AddVehicleRequest request) {
        VehicleResponse response = vehicleService.addVehicle(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable long id) {
        VehicleResponse response = vehicleService.getVehicle(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable long id, @RequestBody UpdateVehicleRequest request) {
        VehicleResponse response = vehicleService.updateVehicle(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> response = vehicleService.getAllVehicles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<Vehicle> getMostExpensive() {
        Vehicle mostExpensive = vehicleService.getMostExpensive();
        return ResponseEntity.ok(mostExpensive);
    }

    @GetMapping("/least-expensive")
    public ResponseEntity<Vehicle> getLeastExpensive() {
        Vehicle leastExpensive = vehicleService.getLeastExpensive();
        return ResponseEntity.ok(leastExpensive);
    }
}
