package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.dto.response.AddRoomResponse;
import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import com.kdu.smarthome.model.HouseEntity;
import com.kdu.smarthome.model.RoomEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoomService {

    private RoomRepository roomRepository;
    private HouseRepository houseRepository;
    private UserRepository userRepository;

    @Autowired
    public RoomService(HouseRepository houseRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        log.info("RoomService initialized with HouseRepository, UserRepository, and RoomRepository");
    }

    /**
     * Adds a new room to a specified house.
     *
     * @param id The ID of the house where the room is to be added.
     * @param addRoomRequest The request data transfer object containing the room details.
     * @return AddRoomResponse A response data transfer object containing details of the added room.
     * @throws ResourceNotFoundException If the specified house does not exist.
     */
    public AddRoomResponse addRooms(String id, AddRoomRequest addRoomRequest) throws ResourceNotFoundException {
        log.debug("[RoomService - addRooms] Processing request to add a new room.");

        // Fetching username from JWT security context
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("[RoomService - addRooms] Authenticated username: {}", authenticatedUsername);

        // Verifying the existence of the house
        Long houseID = Long.parseLong(id);
        HouseEntity house = houseRepository.findById(houseID)
                .orElseThrow(() -> new ResourceNotFoundException("House ID does not exist: " + id));
        log.info("[RoomService - addRooms] House verified for ID: {}", houseID);

        // Creating and saving the new room
        RoomEntity newRoom = new RoomEntity(addRoomRequest.getRoomName());
        newRoom = roomRepository.save(newRoom);
        log.debug("[RoomService - addRooms] New room created: {}", newRoom);

        // Adding the new room to the house and saving
        List<RoomEntity> roomsList = house.getRoomsList();
        roomsList.add(newRoom);
        house.setRoomsList(roomsList);
        houseRepository.save(house);
        log.info("[RoomService - addRooms] New room added to house and saved. Room ID: {}", newRoom.getId());

        return new AddRoomResponse("Room added successfully!", newRoom, HttpStatus.OK);
    }
}
