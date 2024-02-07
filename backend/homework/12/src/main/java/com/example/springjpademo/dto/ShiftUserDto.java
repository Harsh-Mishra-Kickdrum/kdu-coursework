package com.example.springjpademo.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO for the association between a shift and a user.
 * This encapsulates the linkage, along with audit information.
 */
@Data
public class ShiftUserDto {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID tenantId;
}
