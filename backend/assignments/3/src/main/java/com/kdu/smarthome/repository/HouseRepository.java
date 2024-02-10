package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * HouseRepository interface for handling data access operations for Houses.
 * Extends JpaRepository to leverage Spring Data JPA's CRUD operations and query
 * capabilities for House entities.
 */
@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {

}
