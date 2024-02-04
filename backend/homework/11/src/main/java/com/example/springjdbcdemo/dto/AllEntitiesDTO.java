package com.example.springjdbcdemo.dto;

import lombok.Data;

/**
 * A container DTO that encapsulates all entities related to a tenant's shift management.
 * This DTO is particularly useful for operations where we need to transmit a comprehensive
 * view of a tenant's data in a single object, such as when saving all entities in one go.
 */
@Data
public class AllEntitiesDTO {
    private ShiftTypeDto shiftTypeDto;
    private ShiftDto shiftDto;
    private UserDto userDto;
    private ShiftUserDto shiftUserDto;
}
