package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Represents the inventory entity for devices in the system.
 * This entity tracks each device's details, including identification, authentication, manufacture information, and lifecycle timestamps.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device_inventory", indexes = {
        @Index(name = "kickston_id_index", columnList = "kickston_id", unique = true)})

/*
 * About SQLDelete :
 * The @SQLDelete annotation is a feature provided by Hibernate, which is a popular ORM (Object-Relational Mapping)
 * framework for Java. This annotation allows us to customize the SQL operation that Hibernate executes when an
 * entity is deleted through a Hibernate session. Instead of physically removing the entity from the database,
 * @SQLDelete enables a "soft delete" operation, where the entity is marked as deleted in the database rather
 * than being removed. This approach is often used
 * to retain deleted records for historical or audit purposes, ensuring data can be recovered or audited later
 * if necessary.
 */

@SQLDelete(sql = "UPDATE device_inventory SET deleted = true WHERE id = ?")
public class DeviceInventoryEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInventoryEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kickston_id", nullable = false, unique = true)
    private String kickStoneId;

    @Column(name = "device_username", nullable = false)
    private String deviceUserName;

    @Column(name = "device_password", nullable = false)
    private String devicePassword;

    @Column(name = "manufacture_date_time", nullable = false)
    private String manufactureDateTime;

    @Column(name = "manufacture_factory_place", nullable = false)
    private String manufactureFactoryPlace;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * Constructs a new DeviceInventoryEntity with specified details.
     *
     * @param kickStoneId             Unique identifier for the device.
     * @param deviceUserName          Username for device access.
     * @param devicePassword          Password for device access.
     * @param manufactureDateTime     Manufacture date and time.
     * @param manufactureFactoryPlace Place of manufacture.
     */
    public DeviceInventoryEntity(String kickStoneId, String deviceUserName, String devicePassword, String manufactureDateTime, String manufactureFactoryPlace) {
        this.kickStoneId = kickStoneId;
        this.deviceUserName = deviceUserName;
        this.devicePassword = devicePassword;
        this.manufactureDateTime = manufactureDateTime;
        this.manufactureFactoryPlace = manufactureFactoryPlace;
        LOGGER.info("DeviceInventoryEntity created: {}", this);
    }

}
