package com.example.springjdbcdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents the type of shifts available in the system.
 */
@Data
@RequiredArgsConstructor
public class ShiftType {
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
