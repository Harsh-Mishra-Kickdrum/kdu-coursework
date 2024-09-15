package com.example.springjpademo.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Data transfer object for ShiftType.
 * It carries the shift type details, including its unique identifier, description, and status.
 */
@Data
public class ShiftTypeDto {
    private UUID id;
    private String uqName;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;
}
