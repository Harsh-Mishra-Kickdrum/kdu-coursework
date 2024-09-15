package com.example.springjpademo.repository;

import com.example.springjpademo.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for {@link ShiftType} entities.
 * Extends JpaRepository to provide CRUD operations and more on ShiftType entities.
 */
@Repository
public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {

}
