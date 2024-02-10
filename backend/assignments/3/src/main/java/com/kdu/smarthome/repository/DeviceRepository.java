package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.DevicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DeviceRepository interface for handling data access operations for Devices.
 * Extends JpaRepository to provide CRUD operations and more for Device entities.
 */
@Repository
public interface DeviceRepository extends JpaRepository<DevicesEntity, Long> {

    /**
     * Finds a Device entity based on its unique KickStone ID.
     *
     * @param kickStoneId the unique identifier for the Device.
     * @return the Device entity if found, or {@code null} if no Device with the given KickStone ID exists.
     */
    DevicesEntity findDeviceByKickStoneId(String kickStoneId);
}
