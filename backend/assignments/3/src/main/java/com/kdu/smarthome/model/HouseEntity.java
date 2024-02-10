package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a house entity in the smart home system.
 * It includes information about the house, such as address, name, associated users, and devices.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house", indexes = {@Index(name = "house_name_index", columnList="house_name", unique = true)})

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

@SQLDelete(sql = "UPDATE house SET deleted = true WHERE id=?")
public class HouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "house_name", nullable = false)
    private String houseName;

    // Assuming UserEntity is your user entity class
    @OneToOne
    @JoinColumn(name = "admin_user")
    private UserEntity user;

    @OneToMany
    @JoinColumn(name = "users_access_list")
    private List<UserEntity> userAccessList = new ArrayList<>();

    // Assuming RoomEntity and DeviceEntity are your room and device entity classes
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rooms_list")
    private List<RoomEntity> roomsList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "devices_list")
    private List<DevicesEntity> devices = new ArrayList<>();

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name="deleted")
    private Boolean deleted = false;

    /**
     * Constructs a new HouseEntity with its address, name, and admin user.
     * @param address The physical address of the house.
     * @param houseName The name of the house.
     * @param user The admin user of the house.
     */
    public HouseEntity(String address, String houseName, UserEntity user){
        this.address = address;
        this.houseName = houseName;
        this.user = user;
    }
}
