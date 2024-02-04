package com.example.springjdbcdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Configuration class for setting up application-specific beans and configurations.
 * It includes the configuration for the DataSource bean, which is essential for JDBC operations.
 */
@Configuration
@ComponentScan("com.example.springjdbcdemo")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Configures and returns the DataSource bean used for database connections.
     * The configuration is read from application properties, allowing for easy adjustments
     * without code changes.
     *
     * @return Configured DataSource bean for database operations.
     */
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(databaseUrl);
        driverManagerDataSource.setUsername(databaseUsername);
        driverManagerDataSource.setPassword(databasePassword);
        driverManagerDataSource.setDriverClassName(driverClassName);
        return driverManagerDataSource;
    }
}
