package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.MoveDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.dto.response.AddDeviceResponse;
import com.kdu.smarthome.dto.response.DeviceRegisterResponse;
import com.kdu.smarthome.service.DeviceService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kdu.smarthome.constants.Constants.MESSAGE;

@RestController
@RequestMapping("/api/v1/device")
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Registers a new device.
     *
     * @param registerDeviceRequest The device registration request details.
     * @return A response entity with the registration response.
     */
    @PostMapping("/register")
    public ResponseEntity<DeviceRegisterResponse> registerDevice(@Valid @RequestBody DeviceRegisterRequest registerDeviceRequest) {
        log.info("POST: /api/v1/device/register");
        log.debug("Request data: {}", registerDeviceRequest);

        DeviceRegisterResponse registerDeviceResponse = deviceService.registerDevice(registerDeviceRequest);

        return new ResponseEntity<>(registerDeviceResponse, HttpStatus.OK);
    }

    /**
     * Adds a device to a house.
     *
     * @param addDeviceRequest The request details for adding a device.
     * @return A response entity with the add device response.
     */
    @PostMapping("/add")
    public ResponseEntity<AddDeviceResponse> addDeviceInHouse(@Valid @RequestBody AddDeviceRequest addDeviceRequest) {
        log.info("POST: /api/v1/device/add");
        log.debug(MESSAGE, addDeviceRequest);

        AddDeviceResponse addDeviceResponse = deviceService.addDevice(addDeviceRequest);
        return new ResponseEntity<>(addDeviceResponse, HttpStatus.OK);
    }

    /**
     * Moves a device from one location to another.
     *
     * @param moveDeviceRequest The request details for moving a device.
     * @return A response entity indicating success.
     */
    @PostMapping("/move")
    public ResponseEntity<String> moveDevice(@Valid @RequestBody MoveDeviceRequest moveDeviceRequest) {
        log.info("POST: /api/v1/device/move");
        log.debug(MESSAGE, moveDeviceRequest);

        deviceService.moveDevice(moveDeviceRequest);
        return new ResponseEntity<>("Device moved successfully!!", HttpStatus.OK);
    }
}
