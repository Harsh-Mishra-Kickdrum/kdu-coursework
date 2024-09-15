package com.example.springjpademo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Entity class representing a mapping between a shift and a user.
 * This indicates which users are assigned to which shifts.
 */
@Entity
@Table(name = "shift_users")
@Data
@NoArgsConstructor
public class ShiftUser {

    /**
     * Unique identifier for the ShiftUser. It is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Many-to-One relationship to the Shift entity.
     * Indicates the shift to which the user is assigned.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_id", referencedColumnName = "id")
    private Shift shift;

    /**
     * Many-to-One relationship to the User entity.
     * Indicates the user who is assigned to the shift.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * User who created the ShiftUser record. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String createdBy;

    /**
     * User who last updated the ShiftUser record. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private String updatedBy;

    /**
     * Timestamp when the ShiftUser was created. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private OffsetDateTime createdAt;

    /**
     * Timestamp when the ShiftUser was last updated. This field is not explicitly mapped, so it will default to a column
     * with the same name as the field.
     */
    private OffsetDateTime updatedAt;

    /**
     * Identifier for the tenant that owns this ShiftUser, facilitating multi-tenancy.
     * This field is not explicitly mapped, so it will default to a column with the same name as the field.
     */
    private UUID tenantId;
}
