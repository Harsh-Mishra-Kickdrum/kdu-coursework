package com.example.springjpademo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity class representing the types of shifts available in the system.
 * It is mapped to the "shift_type" table in the database.
 */
@Entity
@Table(name = "shift_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType {

    /**
     * Unique identifier for the ShiftType. It is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Unique name for the ShiftType, cannot be null. Mapped to the "name" column in the database.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String uqName;

    /**
     * Description of the ShiftType. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String description;

    /**
     * Indicates whether the ShiftType is active. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private boolean active;

    /**
     * User who created the ShiftType record. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String createdBy;

    /**
     * User who last updated the ShiftType record. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String updatedBy;

    /**
     * Timestamp when the ShiftType was created. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private OffsetDateTime createdAt;

    /**
     * Timestamp when the ShiftType was last updated. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private OffsetDateTime updatedAt;

    /**
     * Timezone of the ShiftType. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String timeZone;

    /**
     * Identifier for the tenant that owns this ShiftType, facilitating multi-tenancy.
     * Mapped to the "tenant_id" column in the database and marked as non-nullable.
     */
    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;
}
