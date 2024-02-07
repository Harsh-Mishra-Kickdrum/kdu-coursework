package com.example.springjpademo.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * User data transfer object.
 * Contains user-specific information, including login status and associated tenant.
 */
@Data
public class UserDto {
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID tenantId;
}
