package com.example.springjpademo.dto;

import lombok.Data;

/**
 * A Data Transfer Object (DTO) that encapsulates all entities related to a tenant's shift management.

 */
@Data
public class AllEntitiesDTO {

    /**
     * DTO representing various shift types available within the system.
     */
    private ShiftTypeDto shiftTypeDto;

    /**
     * DTO for shift details, including the shift's timing, type, and any
     */
    private ShiftDto shiftDto;

    /**
     * User DTO encapsulating user-specific information, such as user identification,
     * roles, and other metadata relevant to the user within the tenant's ecosystem.
     */
    private UserDto userDto;

    /**
     * DTO for mapping between shifts and users, detailing which users are assigned
     * to specific shifts, thereby facilitating the management of shift assignments.
     */
    private ShiftUserDto shiftUserDto;

}
