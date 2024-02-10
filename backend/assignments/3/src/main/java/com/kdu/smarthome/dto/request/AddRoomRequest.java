package com.kdu.smarthome.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to add a new room.
 * It includes all necessary details for creating a room within a house.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {

    @NotBlank(message = "roomName field should not be empty")
    private String roomName;
}
