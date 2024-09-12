package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity class for Devices with JPA annotations for ORM functionality.
 * Includes basic entity information and auditing fields.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices", indexes = {
        @Index(name = "kickston_id_index", columnList="kickston_id", unique = true)
})

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
@SQLDelete(sql = "UPDATE devices SET deleted = true WHERE id=?")
public class DevicesEntity {

    private static final Logger log = LoggerFactory.getLogger(DevicesEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kickston_id", unique = true, nullable = false)
    private String kickstonId;

    @Column(name = "device_user_name")
    private String deviceUsername;

    @Column(name = "user_name")
    private String username;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * Constructs a new DevicesEntity with specified kickstonId and username.
     *
     * @param kickstonId the unique identifier for the device
     * @param username the username associated with the device
     */
    public DevicesEntity(String kickstonId, String username) {
        this.kickstonId = kickstonId;
        this.username = username;
        log.info("DevicesEntity created with kickstonId: {} and username: {}", kickstonId, username);
    }

    /**
     * Constructs a new DevicesEntity with specified kickstonId, username, and deviceUsername.
     *
     * @param kickstonId the unique identifier for the device
     * @param username the username associated with the device
     * @param deviceUsername the device-specific username
     */
    public DevicesEntity(String kickstonId, String username, String deviceUsername) {
        this.kickstonId = kickstonId;
        this.username = username;
        this.deviceUsername = deviceUsername;
        log.info("DevicesEntity created with kickstonId: {}, username: {}, and deviceUsername: {}", kickstonId, username, deviceUsername);
    }
}
