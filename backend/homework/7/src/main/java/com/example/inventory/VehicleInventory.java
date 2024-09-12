package com.example.inventory;

import com.example.model.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * We have created vehicleInventory as Component and given scope as prototype so that it creates new instance every time
 * it is been called.
 */
@Component
@Scope("prototype")
public class VehicleInventory {

    private List<Vehicle> vehicles;

    public VehicleInventory(){
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicle(){
        return vehicles;
    }

    /**
     * For finding the highest priced vehicle
     * @return The vehicle with the highest price
     */
    public Vehicle findHighestPricedVehicle() {
        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }

    /**
     * For finding the lowest priced vehicle
     * @return the vehicles with the lowest price
     */
    public Vehicle findLowestPricedVehicle() {
        return vehicles.stream()
                .min(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }

}
