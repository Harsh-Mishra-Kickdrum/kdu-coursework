package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository interface for handling data access operations related to users.
 * Extends JpaRepository to leverage CRUD operations and more for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Finds a User entity by its username.
     *
     * @param username the username of the User.
     * @return the User entity if found, or {@code null} if no User with the given username exists.
     */
    UserEntity findByUsername(String username);
}
