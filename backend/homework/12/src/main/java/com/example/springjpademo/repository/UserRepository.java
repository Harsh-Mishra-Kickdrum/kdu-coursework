package com.example.springjpademo.repository;

import com.example.springjpademo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository interface for {@link User} entity.
 * It extends {@link JpaRepository} to leverage standard CRUD and pagination functionalities,
 * along with custom queries for user-specific operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds all users with pagination support.
     * This method leverages Spring Data's automatic query derivation to implement pagination
     * and sorting for user entities.
     *
     * @param pageable Abstract interface for pagination information.
     * @return A page of {@link User} entities.
     */
    Page<User> findAll(Pageable pageable);


    /**
     * Custom query to update a user's username.
     * This method demonstrates the use of a modifying query along with a native SQL query
     * for updating a specific field of an entity based on its UUID.
     *
     * @param userId The UUID of the user whose username is to be updated.
     * @param username The new username to be set.
     * @return The number of entities updated.
     */
    @Modifying
    @Query(value = "UPDATE users SET username = :username WHERE id = :userId", nativeQuery = true)
    int updateUserUsername(UUID userId, String username);
}
