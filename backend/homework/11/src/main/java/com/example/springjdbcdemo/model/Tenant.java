package com.example.springjdbcdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents a tenant in the system.
 * A tenant can be thought of as an independent entity or organization
 * using the application, with its own set of users, shifts, and shift types.
 */
@Data
@RequiredArgsConstructor
public class Tenant {
    private UUID id;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
