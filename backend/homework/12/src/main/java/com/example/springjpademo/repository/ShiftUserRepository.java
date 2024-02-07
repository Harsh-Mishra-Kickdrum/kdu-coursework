package com.example.springjpademo.repository;

import com.example.springjpademo.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

/**
 * Repository interface for {@link ShiftUser} entity. It extends {@link JpaRepository} to provide
 * standard data access functionalities with additional custom ones for {@code ShiftUser} entities.
 */
@Repository
public interface ShiftUserRepository extends JpaRepository<ShiftUser, UUID> {

    /**
     * Custom method to delete {@link ShiftUser} entities based on the user's UUID.
     * This method provides a direct way to remove all shift assignments for a specific user.
     *
     * @param userId The UUID of the user for whom related {@code ShiftUser} entities should be deleted.
     */
    @Transactional
    void deleteByUserId(UUID userId);
}
