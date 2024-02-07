package com.example.springjpademo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a user in the system, including login status and associated tenant.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor // JPA entities should have a no-argument constructor for framework use.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "logged_in", nullable = false)
    private boolean loggedIn; // Using boolean type for clarity (true or false for logged in or not)

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;
}
