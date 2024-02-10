package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddHouseUserRequest;
import com.kdu.smarthome.dto.request.AddHouseRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.dto.response.*;

import com.kdu.smarthome.service.HouseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house")
@Slf4j
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Adds a house.
     *
     * @param addHouseRequest The house addition request details.
     * @return A response entity with the addition response.
     */
    @PostMapping()
    public ResponseEntity<AddHouseResponse> addHouse(@Valid @RequestBody AddHouseRequest addHouseRequest) {
        log.info("POST: /api/v1/house");
        log.debug("Request data: {}", addHouseRequest);

        AddHouseResponse addHouseResponse = houseService.addHouse(addHouseRequest);
        return new ResponseEntity<>(addHouseResponse, HttpStatus.OK);
    }

    /**
     * Retrieves all houses.
     *
     * @return A response entity with all houses.
     */
    @GetMapping()
    public ResponseEntity<ListHouseResponse> getAllHouses() {
        log.info("GET: /api/v1/house");

        ListHouseResponse getAllHouseResponse = houseService.getAllHouses();
        return new ResponseEntity<>(getAllHouseResponse, HttpStatus.OK);
    }

    /**
     * Retrieves all rooms and devices for a given house ID.
     *
     * @param houseId The ID of the house.
     * @return A response entity with all details for the house.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<ListAllDetailsResponse> getAllRoomsAndDevices(@PathVariable Long houseId) {
        log.info("GET: /api/v1/house/{}", houseId);

        ListAllDetailsResponse getAllDetailsResponse = houseService.getAllRoomsAndDevices(houseId);
        return new ResponseEntity<>(getAllDetailsResponse, HttpStatus.OK);
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId The ID of the house to update.
     * @param updateAddressRequest The new address details.
     * @return A response entity with the updated address response.
     */
    @PutMapping("/{houseId}/update-address")
    public ResponseEntity<UpdateAddressResponse> updateAddress(@PathVariable Long houseId,
                                                               @Valid @RequestBody UpdateAddressRequest updateAddressRequest) {
        log.info("PUT: /api/v1/house/{}/update-address", houseId);
        log.debug("House ID: {} & Request data: {}", houseId, updateAddressRequest);

        UpdateAddressResponse updateAddressResponse = houseService.updateAddress(houseId, updateAddressRequest);
        return new ResponseEntity<>(updateAddressResponse, HttpStatus.OK);
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId The ID of the house.
     * @param addHomeUserRequest The user addition request details.
     * @return A response entity with the user addition response.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<AddHouseUserResponse> addUserToHouse(@PathVariable Long houseId,
                                                              @Valid @RequestBody AddHouseUserRequest addHomeUserRequest) {
        log.info("POST: /api/v1/house/{}/add-user", houseId);
        log.debug("House ID: {} & Request data: {}", houseId, addHomeUserRequest);

        AddHouseUserResponse addHomeUserResponse = houseService.addHomeUser(houseId, addHomeUserRequest);
        return new ResponseEntity<>(addHomeUserResponse, HttpStatus.OK);
    }
}
