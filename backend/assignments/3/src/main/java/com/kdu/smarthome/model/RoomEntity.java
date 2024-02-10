package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

/**
 * Represents a room entity in the smart home system.
 * It includes information about the room, such as its name and timestamps for creation and updates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms", indexes = {@Index(name = "room_name_index", columnList="room_name", unique = true)})

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

@SQLDelete(sql = "UPDATE rooms SET deleted = true WHERE id=?")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_name", nullable = false, unique = true)
    private String roomName;

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name="deleted")
    private Boolean deleted = false;

    /**
     * Constructs a new RoomEntity with its name.
     * @param roomName The name of the room.
     */
    public RoomEntity(String roomName){
        this.roomName = roomName;
    }
}
