package com.example.springsecuritydemo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide beans.
 * This class is responsible for setting up beans that are not specific to any particular
 * part of the application but are essential for the overall functioning and efficiency of the application.
 */
@Configuration
public class AppConfig {

    /**
     * Bean configuration for ModelMapper.
     *
     * ModelMapper is a library used to automate the mapping of objects from one class
     * type to another based on their properties. This bean is used application-wide
     * to facilitate easy conversion between different object models, such as entities and DTOs.
     *
     * @return A new instance of ModelMapper for use throughout the application.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
