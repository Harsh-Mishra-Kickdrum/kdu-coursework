package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.DeviceInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * InventoryRepository interface for handling data access operations related to device inventories.
 * Extends JpaRepository to leverage CRUD operations and more for DeviceInventory entities.
 */
@Repository
public interface InventoryRepository extends JpaRepository<DeviceInventoryEntity, Long> {

    /**
     * Finds a DeviceInventory entity based on its unique KickStone ID.
     *
     * @param kickStoneId The unique identifier for the DeviceInventory.
     * @return The DeviceInventory entity if found, or {@code null} if no DeviceInventory with the given KickStone ID exists.
     */
    DeviceInventoryEntity findDeviceInventoryByKickStoneId(String kickStoneId);
}
