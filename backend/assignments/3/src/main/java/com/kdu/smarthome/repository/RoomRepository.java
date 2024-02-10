package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoomRepository interface for handling data access operations related to rooms.
 * Extends JpaRepository to leverage CRUD operations and more for Room entities.
 */
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

}
