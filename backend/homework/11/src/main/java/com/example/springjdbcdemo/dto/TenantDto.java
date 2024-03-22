package com.example.springjdbcdemo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Tenant Data Transfer Object.
 * <p>
 * This class represents a data transfer object for the Tenant entity. It is used to transfer tenant data
 * between processes, serving as a data encapsulation layer that simplifies the exchange of information.
 * The TenantDto includes essential tenant information along with audit data such as creation and update timestamps.
 * </p>
 */
@Data
@RequiredArgsConstructor
public class TenantDto {
    private UUID id;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
