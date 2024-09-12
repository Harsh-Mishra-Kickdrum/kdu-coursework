package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.dto.response.AddRoomResponse;
import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import com.kdu.smarthome.service.RoomService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Adds a room to a house.
     *
     * @param addRoomRequestDTO The request DTO for adding a room.
     * @param houseId The ID of the house to which the room is being added.
     * @return A ResponseEntity containing the AddRoomResponseDTO.
     */
    @PostMapping
    public ResponseEntity<AddRoomResponse> addRoom(@Valid @RequestBody AddRoomRequest addRoomRequestDTO, @RequestParam String houseId) throws  ResourceNotFoundException {
        log.info("POST: /api/v1/room - House ID: {}", houseId);

        AddRoomResponse addRoomResponseDTO = roomService.addRooms(houseId, addRoomRequestDTO);
        return new ResponseEntity<>(addRoomResponseDTO, HttpStatus.OK);
    }
}
