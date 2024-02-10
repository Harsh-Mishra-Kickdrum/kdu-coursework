package com.kdu.smarthome.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = {@Index(name = "username_index", columnList="username", unique = true)})
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "emailId", nullable = false)
    private String emailId;

    // The @Type annotation with type = "json" is for Hibernate to recognize and handle the JSON column types
    // This is crucial for correctly mapping the List<HouseEntity> to a JSON column in the database.
    @Type(type = "json")
    @Column(name = "ownHouseList", columnDefinition = "json")
    private List<HouseEntity> ownHouseList = new ArrayList<>();

    @Type(type = "json")
    @Column(name = "othersHouseList", columnDefinition = "json")
    private List<HouseEntity> othersHouseList = new ArrayList<>();

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name="deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * Constructor used for user registration.
     *
     * @param username the user's username
     * @param password the user's password
     */
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Full constructor for all user details.
     *
     * @param username the user's username
     * @param password the user's password
     * @param name the user's full name
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param emailId the user's email ID
     */
    public UserEntity(String username, String password, String name, String firstName, String lastName, String emailId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
}
