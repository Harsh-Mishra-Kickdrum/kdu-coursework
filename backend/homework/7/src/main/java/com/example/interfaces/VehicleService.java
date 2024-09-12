package com.example.interfaces;

import com.example.model.Vehicle;

public interface VehicleService {
    void generateVehicleList();
    Vehicle findMostExpensiveVehicle();
    Vehicle findLeastExpensiveVehicle();
}
