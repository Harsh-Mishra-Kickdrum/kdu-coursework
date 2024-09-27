package com.example.springhandson3.repository;

import com.example.springhandson3.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class VehicleRepository {

    private final Map<Long, Vehicle> vehicleMap = new ConcurrentHashMap<>();
    //Used for the new vehicles if they are added with id = 0
    private long idCounter = 1;

    /**
     * For adding and updating the vehicle
     * @param vehicle the vehicle to be added or updated
     * @return it returns the vehicle
     */
    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == 0) {
            vehicle.setId(idCounter++);
        }
        vehicleMap.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    /**
     * For finding the vehicle by id
     * @param id the id of the vehicle
     * @return returns the vehicle for of the particular id
     */

    public Vehicle findById(long id) {
        return vehicleMap.get(id);
    }

    /**
     * For deleting the vehicle
     * @param id the id of the vehicle to be deleted
     */
    public void deleteById(long id) {
        vehicleMap.remove(id);
    }

    /**
     * To find all vehicles
     * @return the list of all vehicles
     */
    public List<Vehicle> findAll() {
        return vehicleMap.values().stream().toList();
    }


    public Vehicle findMostExpensive() {
        return vehicleMap.values().stream()
                .max(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }


    public Vehicle findLeastExpensive() {
        return vehicleMap.values().stream()
                .min(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }
}
