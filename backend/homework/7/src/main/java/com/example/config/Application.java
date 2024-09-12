package com.example.config;

import com.example.interfaces.VehicleService;
import com.example.inventory.VehicleInventory;
import com.example.logging.Logging;
import com.example.model.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class Application {

    private final VehicleService factory1Service;
    private final VehicleService factory2Service;
    private final VehicleInventory vehicleInventoryFactory1;
    private final VehicleInventory vehicleInventoryFactory2;

    @Autowired
    public Application(
            @Qualifier("factory1") VehicleService factory1Service,
            @Qualifier("factory2") VehicleService factory2Service,
            @Qualifier("factory1Inventory") VehicleInventory vehicleInventoryFactory1,
            @Qualifier("factory2Inventory") VehicleInventory vehicleInventoryFactory2) {
        this.factory1Service = factory1Service;
        this.factory2Service = factory2Service;
        this.vehicleInventoryFactory1 = vehicleInventoryFactory1;
        this.vehicleInventoryFactory2 = vehicleInventoryFactory2;
    }

    @PostConstruct
    public void printVehicleDetails() {
        // Print Factory1 details
        printFactoryDetails(factory1Service, vehicleInventoryFactory1, "Factory1");

        // Print Factory2 details
        printFactoryDetails(factory2Service, vehicleInventoryFactory2, "Factory2");

        // Identify and print the details of the most and least expensive vehicles overall
        Vehicle mostExpensiveVehicleOverall = findMostExpensiveVehicleOverall();
        Vehicle leastExpensiveVehicleOverall = findLeastExpensiveVehicleOverall();

       Logging.getmsg().info("Most Expensive Vehicle Overall Details: {}", mostExpensiveVehicleOverall);
       Logging.getmsg().info("Least Expensive Vehicle Overall Details: {}", leastExpensiveVehicleOverall);


        // Explicitly print the highest and lowest prices
        Logging.getmsg().info("Highest Price: {}", mostExpensiveVehicleOverall.getPrice());
        Logging.getmsg().info("Lowest Price: {}", leastExpensiveVehicleOverall.getPrice());
    }


    private void printFactoryDetails(VehicleService factoryService, VehicleInventory vehicleInventory, String factoryName) {
        // Generate and print the details of the most expensive and least expensive vehicles for each factory
        Vehicle mostExpensiveFactoryVehicle = factoryService.findMostExpensiveVehicle();
        Vehicle leastExpensiveFactoryVehicle = factoryService.findLeastExpensiveVehicle();

        Logging.getmsg().info("Most Expensive {} Vehicle Details: {}", factoryName, mostExpensiveFactoryVehicle);
        Logging.getmsg().info("Least Expensive {} Vehicle Details: {}", factoryName, leastExpensiveFactoryVehicle);
    }


    public static void main(String[] args) {
        // Create and configure the Spring context using AppConfig
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the beans from the context
        VehicleService factory1Service = context.getBean("factory1", VehicleService.class);
        VehicleService factory2Service = context.getBean("factory2", VehicleService.class);
        VehicleInventory vehicleInventoryFactory1 = context.getBean("factory1Inventory", VehicleInventory.class);
        VehicleInventory vehicleInventoryFactory2 = context.getBean("factory2Inventory", VehicleInventory.class);

        // Generate the vehicles using VehicleService for each factory
        factory1Service.generateVehicleList();
        factory2Service.generateVehicleList();

        // Create the application instance and pass dependencies
        Application application = new Application(factory1Service, factory2Service, vehicleInventoryFactory1, vehicleInventoryFactory2);

        // Initialize the application
        application.init();
    }

    private void init() {
        Logging.getmsg().info("Application initialized.");
    }

    private Vehicle findMostExpensiveVehicleOverall() {
        Vehicle mostExpensiveFactory1Vehicle = vehicleInventoryFactory1.findHighestPricedVehicle();
        Vehicle mostExpensiveFactory2Vehicle = vehicleInventoryFactory2.findHighestPricedVehicle();

        return compareVehicles(mostExpensiveFactory1Vehicle, mostExpensiveFactory2Vehicle);
    }

    private Vehicle findLeastExpensiveVehicleOverall() {
        Vehicle leastExpensiveFactory1Vehicle = vehicleInventoryFactory1.findLowestPricedVehicle();
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
