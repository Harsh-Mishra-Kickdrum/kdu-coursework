package com.example.springsecuritydemo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user entity with username, password, email, and role attributes.
 */
@Getter
@Setter
public class User {
    private String userName;
    private String password;
    private String email;
    private String role; // Role attribute for ROLE based authentication

    /**
     * Constructs a new User with the specified username, password, and role.
     * The email field is not set in the constructor and can be set using its setter method.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @param role     the role of the user, used for role-based access control.
     */
    public User(String username, String password, String role) {
        this.userName = username;
        this.password = password;
        this.role = role;
    }

    // Lombok annotations @Getter and @Setter automatically generate getter and setter methods.
}
