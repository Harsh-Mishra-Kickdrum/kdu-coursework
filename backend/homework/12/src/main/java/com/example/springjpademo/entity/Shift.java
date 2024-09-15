package com.example.springjpademo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Represents a Shift entity within the system, mapping it to a "shift" table in the database.
 * Each Shift is associated with a specific ShiftType and contains information about its timing and duration.
 */
@Entity
@Table(name = "shift")
@Data
@NoArgsConstructor // JPA requires a no-arg constructor
public class Shift {

    /**
     * Unique identifier for the Shift. It is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The type of the shift, representing a many-to-one relationship with the ShiftType entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftTypeId;

    /**
     * Name or description of the shift.
     */
    private String name;

    /**
     * The start date of the shift. Only includes date information, no time.
     */
    private LocalDate startDate;

    /**
     * The end date of the shift. Only includes date information, no time.
     */
    private LocalDate endDate;

    /**
     * The start time of the shift. Only includes time information, no date.
     */
    private LocalTime timeStart;

    /**
     * The end time of the shift. Only includes time information, no date.
     */
    private LocalTime timeEnd;

    /**
     * Timestamp when the shift record was created. Includes both date and time information, with timezone offset.
     */
    private OffsetDateTime createdAt;

    /**
     * Timestamp when the shift record was last updated. Includes both date and time information, with timezone offset.
     */
    private OffsetDateTime updatedAt;

    /**
     * The timezone of the shift, affecting how start and end times are interpreted.
     */
    private String timeZone;

    /**
     * Identifier for the tenant that owns this shift, facilitating multi-tenancy.
     */
    private UUID tenantId;

}
