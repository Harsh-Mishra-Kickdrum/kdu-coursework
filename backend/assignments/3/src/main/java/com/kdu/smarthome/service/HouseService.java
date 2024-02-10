package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddHouseRequest;
import com.kdu.smarthome.dto.request.AddHouseUserRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.dto.response.AddHouseResponse;
import com.kdu.smarthome.dto.response.ListHouseResponse;
import com.kdu.smarthome.dto.response.ListAllDetailsResponse;
import com.kdu.smarthome.dto.response.UpdateAddressResponse;
import com.kdu.smarthome.dto.response.AddHouseUserResponse;
import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import com.kdu.smarthome.model.HouseEntity;
import com.kdu.smarthome.model.UserEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class to handle operations related to houses.
 */
@Service
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    /**
     * Constructor for HouseService.
     *
     * @param houseRepository The repository for house entities.
     * @param userRepository  The repository for user entities.
     */
    public HouseService(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adds a new house.
     *
     * @param addHouseRequest The request to add a house.
     * @return AddHouseResponse containing information about the added house.
     */
    public AddHouseResponse addHouse(AddHouseRequest addHouseRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("[HouseService - addHouse] Username: {}", username);

        UserEntity user = userRepository.findByUsername(username);
        HouseEntity house = new HouseEntity(addHouseRequest.getAddress(), addHouseRequest.getHouseName(), user);
        HouseEntity newHouse = houseRepository.save(house);

        return new AddHouseResponse("Home added successfully!", newHouse, HttpStatus.OK);
    }

    /**
     * Retrieves all houses.
     *
     * @return GetAllHousesResponse containing a list of all houses.
     */
    public ListHouseResponse getAllHouses() {
        List<HouseEntity> houseList = houseRepository.findAll();
        log.debug("[HouseService - getAllHouses] HouseList: {}", houseList);

        return new ListHouseResponse("All houses fetched successfully!", houseList, HttpStatus.OK);
    }

    /**
     * Retrieves details of all rooms and devices in a house.
     *
     * @param houseId The ID of the house.
     * @return GetAllDetailsResponse containing details of rooms and devices in the house.
     */
    public ListAllDetailsResponse getAllRoomsAndDevices(Long houseId) {
        HouseEntity house = houseRepository.findById(houseId)
                .orElse(new HouseEntity());

        return new ListAllDetailsResponse("Data fetched successfully!", house.toString(), HttpStatus.OK);
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId              The ID of the house.
     * @param updateAddressRequest The request containing the new address.
     * @return UpdateAddressResponse containing information about the updated house.
     * @throws ResourceNotFoundException if the house is not found.
     */
    public UpdateAddressResponse updateAddress(Long houseId, UpdateAddressRequest updateAddressRequest) throws ResourceNotFoundException {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("[HouseService - updateAddress] AuthenticatedUsername: {}", authenticatedUsername);

        HouseEntity house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("House not found"));

        house.setAddress(updateAddressRequest.getNewAddress());
        HouseEntity updatedHouse = houseRepository.save(house);

        return new UpdateAddressResponse("House address updated successfully!", updatedHouse.toString(), HttpStatus.OK);
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId            The ID of the house.
     * @param addHomeUserRequest The request containing the username of the user to be added.
     * @return AddHomeUserResponse containing information about the updated house.
     * @throws ResourceNotFoundException if the house or user is not found.
     */
    public AddHouseUserResponse addHomeUser(Long houseId, AddHouseUserRequest addHomeUserRequest) throws ResourceNotFoundException {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("[HouseService - addHomeUser] AuthenticatedUsername: {}", authenticatedUsername);

        HouseEntity house = houseRepository.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("House not found"));

        UserEntity userToAdd = userRepository.findByUsername(addHomeUserRequest.getUsername());
        if(userToAdd==null ||userToAdd.getId() == null){
            throw new ResourceNotFoundException("Failed to add, User with given username does not exists");
        }

        if (!house.getUser().getUsername().equals(authenticatedUsername)) {
            throw new ResourceNotFoundException("Only the house owner can add users.");
        }

        house.getUserAccessList().add(userToAdd);
        HouseEntity updatedHouse = houseRepository.save(house);

        return new AddHouseUserResponse("User added to home successfully!", updatedHouse.toString(), HttpStatus.OK);
    }
}
