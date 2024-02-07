package com.example.springjpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Represents the data transfer object for Shift.
 * It encapsulates all the information related to a shift, including timing, type, and associated tenant.
 */
@Data
@AllArgsConstructor
public class ShiftDto {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;
}
