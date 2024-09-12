// VehicleServiceImpl.java

package com.example.service;

import com.example.interfaces.VehicleService;
import com.example.logging.Logging;
import com.example.model.Speaker;
import com.example.model.Tyre;
import com.example.model.Vehicle;
import com.example.inventory.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final TyreService tyreService;
    private final SpeakerService speakerService;
    private VehicleInventory vehicleInventory;
    private VehicleInventory vehicleInventoryFactory2;
    private final String factoryLocation;

    @Autowired
    public VehicleServiceImpl(@Value("${factory.location}") String factoryLocation,
                              TyreService tyreService, SpeakerService speakerService,
                              @Qualifier("factory2Inventory") VehicleInventory vehicleInventoryFactory2) {
        this.factoryLocation = factoryLocation;
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.vehicleInventoryFactory2 = vehicleInventoryFactory2;
        this.vehicleInventory = new VehicleInventory(); // Initialize vehicleInventory
    }

    @PostConstruct
    public void generateVehicleList() {
        this.vehicleInventory = new VehicleInventory();  // Initialize vehicleInventory
        this.vehicleInventoryFactory2 = new VehicleInventory();  // Initialize vehicleInventoryFactory2

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Tyre tyre = tyreService.generateTyre();
            Speaker speaker = speakerService.generateSpeaker();
            Vehicle vehicle = new Vehicle(tyre, speaker);

            double totalPrice = calculateTotalPrice(vehicle, factoryLocation);

            vehicle.setPrice(totalPrice);
            vehicles.add(vehicle);
            vehicleInventory.addVehicle(vehicle);  // Add to factory 1 inventory
            vehicleInventoryFactory2.addVehicle(vehicle);  // Add to factory 2 inventory
        }
    }

    @Override
    public Vehicle findMostExpensiveVehicle() {
        if (vehicleInventory.getVehicle().isEmpty()) {
            return null;
        }

        return vehicleInventory.findHighestPricedVehicle();
    }

    @Override
    public Vehicle findLeastExpensiveVehicle() {
        if (vehicleInventory.getVehicle().isEmpty()) {
            return null;
        }

        return vehicleInventory.findLowestPricedVehicle();
    }

    public Vehicle findMostExpensiveVehicleFactory2() {
        if (vehicleInventoryFactory2.getVehicle().isEmpty()) {
            return null;
        }

        return vehicleInventoryFactory2.findHighestPricedVehicle();
    }

    public Vehicle findLeastExpensiveVehicleFactory2() {
        if (vehicleInventoryFactory2.getVehicle().isEmpty()) {
            return null;
        }

        return vehicleInventoryFactory2.findLowestPricedVehicle();
    }

    @PostConstruct
    public void printVehicleDetails() {
        // Generate and print the details of the most expensive and least expensive vehicles for each factory
        Vehicle mostExpensiveFactory1Vehicle = findMostExpensiveVehicle();
        Vehicle leastExpensiveFactory1Vehicle = findLeastExpensiveVehicle();

        Logging.getmsg().info("Most Expensive Factory1 Vehicle Details: {}", mostExpensiveFactory1Vehicle);
        Logging.getmsg().info("Least Expensive Factory1 Vehicle Details: {}", leastExpensiveFactory1Vehicle);

        Vehicle mostExpensiveFactory2Vehicle = findMostExpensiveVehicleFactory2();
        Vehicle leastExpensiveFactory2Vehicle = findLeastExpensiveVehicleFactory2();

        Logging.getmsg().info("Most Expensive Factory2 Vehicle Details: {}", mostExpensiveFactory2Vehicle);
        Logging.getmsg().info("Least Expensive Factory2 Vehicle Details: {}", leastExpensiveFactory2Vehicle);

        // Identify and print the details of the most and least expensive vehicles overall
        Vehicle mostExpensiveVehicleOverall = findMostExpensiveVehicleOverall();
        Vehicle leastExpensiveVehicleOverall = findLeastExpensiveVehicleOverall();

        Logging.getmsg().info("Most Expensive Vehicle Overall Details: {}", mostExpensiveVehicleOverall);
        Logging.getmsg().info("Least Expensive Vehicle Overall Details: {}", leastExpensiveVehicleOverall);

        // Explicitly print the highest and lowest prices
        Logging.getmsg().info("Highest Price Overall: {}", mostExpensiveVehicleOverall.getPrice());
        Logging.getmsg().info("Lowest Price Overall: {}", leastExpensiveVehicleOverall.getPrice());
    }

    private double calculateTotalPrice(Vehicle vehicle, String factoryLocation) {
        double locationFactor = factoryLocation.equals("Factory1") ? 1.1 : 1.0;
        return vehicle.getTyre().getPrice() + vehicle.getSpeaker().getPrice() + (Math.random() * 1000 * locationFactor);
    }

    private Vehicle findMostExpensiveVehicleOverall() {
        Vehicle mostExpensiveFactory1Vehicle = vehicleInventory.findHighestPricedVehicle();
        Vehicle mostExpensiveFactory2Vehicle = vehicleInventoryFactory2.findHighestPricedVehicle();

        return compareVehicles(mostExpensiveFactory1Vehicle, mostExpensiveFactory2Vehicle);
    }

    private Vehicle findLeastExpensiveVehicleOverall() {
        Vehicle leastExpensiveFactory1Vehicle = vehicleInventory.findLowestPricedVehicle();
        Vehicle leastExpensiveFactory2Vehicle = vehicleInventoryFactory2.findLowestPricedVehicle();

        return compareVehicles(leastExpensiveFactory1Vehicle, leastExpensiveFactory2Vehicle);
    }

    private Vehicle compareVehicles(Vehicle vehicle1, Vehicle vehicle2) {
        if (vehicle1 == null) {
            return vehicle2;
        } else if (vehicle2 == null) {
            return vehicle1;
        }

        return (vehicle1.getPrice() >= vehicle2.getPrice()) ? vehicle1 : vehicle2;
    }
}
