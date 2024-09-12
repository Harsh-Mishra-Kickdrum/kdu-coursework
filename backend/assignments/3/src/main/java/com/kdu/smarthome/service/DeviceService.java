package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.dto.request.MoveDeviceRequest;
import com.kdu.smarthome.dto.response.AddDeviceResponse;
import com.kdu.smarthome.dto.response.DeviceRegisterResponse;
import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import com.kdu.smarthome.model.DeviceInventoryEntity;
import com.kdu.smarthome.model.DevicesEntity;
import com.kdu.smarthome.model.HouseEntity;
import com.kdu.smarthome.model.RoomEntity;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing devices within the smart home application.
 * This includes registering new devices, adding devices to rooms, and moving devices between rooms.
 */
@Service
@Slf4j
public class DeviceService {

    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final InventoryRepository inventoryRepository;
    private final DeviceRepository deviceRepository;

    public DeviceService(HouseRepository houseRepository,
                         RoomRepository roomRepository,
                         InventoryRepository inventoryRepository,
                         DeviceRepository deviceRepository) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.inventoryRepository = inventoryRepository;
        this.deviceRepository = deviceRepository;
    }

    /**
     * Registers a new device in the system based on the provided device registration request.
     *
     * @param deviceRegisterRequest The request containing device registration details.
     * @return A response indicating the result of the registration attempt.
     * @throws ResourceNotFoundException if the device credentials are invalid or the device is already registered.
     */
    public DeviceRegisterResponse registerDevice(DeviceRegisterRequest deviceRegisterRequest) throws ResourceNotFoundException {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Attempting to register device for user '{}'", authenticatedUsername);

        String kickstonId = deviceRegisterRequest.getKickstonId();
        String deviceUsername = deviceRegisterRequest.getDeviceUsername();
        String devicePassword = deviceRegisterRequest.getDevicePassword();

        log.debug("Registering device with Kickston ID: {}", kickstonId);

        DevicesEntity history = deviceRepository.findDeviceByKickStoneId(kickstonId);
        if ((history != null) && (history.getUsername() != null)) {
            log.warn("Device with Kickston ID '{}' is already registered", kickstonId);
            throw new ResourceNotFoundException("This device is already registered");
        }

        DeviceInventoryEntity deviceInventory = inventoryRepository.findDeviceInventoryByKickStoneId(kickstonId);

        if (!deviceInventory.getKickStoneId().equals(kickstonId) ||
                !deviceInventory.getDeviceUserName().equals(deviceUsername) ||
                !deviceInventory.getDevicePassword().equals(devicePassword)) {
            log.error("Invalid credentials for device with Kickston ID '{}'", kickstonId);
            throw new ResourceNotFoundException("Invalid Credentials");
        }

        DevicesEntity devicesEntity = new DevicesEntity(kickstonId, authenticatedUsername, deviceUsername);
        DevicesEntity newDevice = deviceRepository.save(devicesEntity);

        log.info("Device registered successfully with Kickston ID: {}", kickstonId);

        return new DeviceRegisterResponse("Device registered successfully!!", newDevice.toString(), HttpStatus.OK);
    }

    /**
     * Adds a registered device to a specified room within a house.
     *
     * @param addDeviceRequest The request containing details about the device, room, and house to add the device to.
     * @return A response indicating the result of the add device attempt.
     * @throws ResourceNotFoundException if the device, room, or house is not found or invalid.
     */
    public AddDeviceResponse addDevice(AddDeviceRequest addDeviceRequest) throws ResourceNotFoundException {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User '{}' is attempting to add a device", authenticatedUsername);

        String kickstonId = addDeviceRequest.getKickstonId();
        String roomId = addDeviceRequest.getRoomId();
        String houseId = addDeviceRequest.getHouseId();

        log.debug("Adding device with Kickston ID '{}' to room ID '{}' in house ID '{}'", kickstonId, roomId, houseId);

        DevicesEntity devicesEntity = deviceRepository.findDeviceByKickStoneId(kickstonId);
        if (devicesEntity == null || devicesEntity.getUsername() == null) {
            log.error("Device with Kickston ID '{}' not registered", kickstonId);
            throw new ResourceNotFoundException("Device not registered");
        }

        if (!devicesEntity.getUsername().equals(authenticatedUsername)) {
            log.error("User '{}' is not the owner of device with Kickston ID '{}'", authenticatedUsername, kickstonId);
            throw new ResourceNotFoundException("Only owner can add devices!!");
        }

        RoomEntity roomEntity = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid room!!"));
        HouseEntity houseEntity = houseRepository.findById(Long.parseLong(houseId))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid House!!"));

        if (!houseEntity.getUser().getName().equals(authenticatedUsername)) {
            log.error("User '{}' is not the owner of house ID '{}'", authenticatedUsername, houseId);
            throw new ResourceNotFoundException("Only owner can add devices!!");
        }

        if (!houseEntity.getRoomsList().contains(roomEntity)) {
            log.error("Room ID '{}' is not part of house ID '{}'", roomId, houseId);
            throw new ResourceNotFoundException("Invalid house and room combination!!");
        }

        devicesEntity.setRoomName(roomEntity.getRoomName());
        DevicesEntity updatedDevice = deviceRepository.save(devicesEntity);

        houseEntity.getDevices().add(updatedDevice);
        houseRepository.save(houseEntity);

        log.info("Device with Kickston ID '{}' successfully added to room '{}'", kickstonId, roomEntity.getRoomName());

        return new AddDeviceResponse("Device installed successfully!!", houseEntity.toString(), HttpStatus.OK);
    }

    /**
     * Moves a device to a different room within the same house or to a new house entirely.
     *
     * @param moveDeviceRequest The request containing the device ID and the target room ID.
     * @throws ResourceNotFoundException if the device or the target room is not found.
     */
    public void moveDevice(MoveDeviceRequest moveDeviceRequest) throws ResourceNotFoundException {
        String deviceId = moveDeviceRequest.getDeviceId();
        String roomId = moveDeviceRequest.getRoomId();

        log.info("Moving device ID '{}' to room ID '{}'", deviceId, roomId);

        DevicesEntity devicesEntity = deviceRepository.findById(Long.parseLong(deviceId))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid device!!"));
        RoomEntity roomEntity = roomRepository.findById(Long.parseLong(roomId))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid room!!"));

        devicesEntity.setRoomName(roomEntity.getRoomName());
        deviceRepository.save(devicesEntity);

        log.info("Device ID '{}' successfully moved to room '{}'", deviceId, roomEntity.getRoomName());
    }
}
