package com.kdu.smarthome.utility;

import com.kdu.smarthome.dto.request.AddInventoryRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.model.DeviceInventoryEntity;
import com.kdu.smarthome.model.UserEntity;

/**
 * Utility class for mapping DTOs to entity objects.
 */
public class Mapper {

    private Mapper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Maps a UserRegisterRequest DTO to a UserEntity.
     *
     * @param userRegisterRequest the DTO containing user registration data
     * @return a UserEntity populated with data from the DTO
     */
    public static UserEntity getUserFromRegisterRequest(UserRegisterRequest userRegisterRequest) {
        return new UserEntity(
                userRegisterRequest.getUsername(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getName(),
                userRegisterRequest.getFirstName(),
                userRegisterRequest.getLastName(),
                userRegisterRequest.getEmailId()
        );
    }

    /**
     * Maps an AddInventoryRequest DTO to a DeviceInventoryEntity.
     *
     * @param addInventoryRequest the DTO containing inventory addition data
     * @return a DeviceInventoryEntity populated with data from the DTO
     */
    public static DeviceInventoryEntity getInventoryFromRequest(AddInventoryRequest addInventoryRequest) {
        return new DeviceInventoryEntity(
                addInventoryRequest.getKickstonId(),
                addInventoryRequest.getDeviceUsername(),
                addInventoryRequest.getDevicePassword(),
                addInventoryRequest.getManufactureDateTime(),
                addInventoryRequest.getManufactureFactoryPlace()
        );
    }
}
