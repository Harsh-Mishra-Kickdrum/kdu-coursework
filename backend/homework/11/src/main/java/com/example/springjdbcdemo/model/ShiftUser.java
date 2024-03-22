package com.example.springjdbcdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents a mapping between a shift and a user, indicating which users are assigned to which shifts.
 */
@Data
@RequiredArgsConstructor
public class ShiftUser {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID tenantId;
}
