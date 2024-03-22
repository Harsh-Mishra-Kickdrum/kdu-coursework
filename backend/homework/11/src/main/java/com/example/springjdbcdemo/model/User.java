package com.example.springjdbcdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Represents a user in the system, including login status and associated tenant.
 */
@Data
@RequiredArgsConstructor
public class User {
    private UUID id;
    private String username;
    private int loggedIn; // 0 or 1 for logged out or logged in
    private String timeZone;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID tenantId;
}
