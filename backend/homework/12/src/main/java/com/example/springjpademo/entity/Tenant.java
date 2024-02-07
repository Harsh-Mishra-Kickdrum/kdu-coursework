package com.example.springjpademo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a tenant in the system.
 * A tenant can be thought of as an independent entity or organization
 * using the application, with its own set of users, shifts, and shift types.
 */
@Entity
@Table(name = "tenant")
@Data
@NoArgsConstructor // JPA entities should have a no-argument constructor for framework use.
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tenantId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy; // Represents the identifier of the user who created the tenant.

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt; // Timestamp of the last update.

    @Column(name = "updated_by")
    private String updatedBy; // Represents the identifier of the user who last updated the tenant.
}
