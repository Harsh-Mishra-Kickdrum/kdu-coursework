// AppConfig.java

package com.example.config;

import com.example.inventory.VehicleInventory;
import com.example.service.SpeakerService;
import com.example.service.TyreService;
import com.example.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Configuration class responsible for defining beans and component scanning.
 */
@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class AppConfig {

    @Bean
    public TyreService tyreService() {
        return new TyreService();
    }

    @Bean
    public SpeakerService speakerService() {
        return new SpeakerService();
    }

    @Bean("factory1")
    @Scope("prototype")
    public VehicleServiceImpl factory1(@Value("${factory1.location}") String factoryLocation,
                                       TyreService tyreService, SpeakerService speakerService,
                                       @Qualifier("factory1Inventory") VehicleInventory vehicleInventory) {
        return new VehicleServiceImpl(factoryLocation, tyreService, speakerService, vehicleInventory);
    }

    @Bean("factory2")
    @Scope("prototype")
    public VehicleServiceImpl factory2(@Value("${factory2.location}") String factoryLocation,
                                       TyreService tyreService, SpeakerService speakerService,
                                       @Qualifier("factory2Inventory") VehicleInventory vehicleInventoryFactory2) {
        return new VehicleServiceImpl(factoryLocation, tyreService, speakerService, vehicleInventoryFactory2);
    }


    @Bean("factory1Inventory")
    public VehicleInventory factory1Inventory() {
        return new VehicleInventory();
    }

    @Bean("factory2Inventory")
    public VehicleInventory factory2Inventory() {
        return new VehicleInventory();
    }
}
