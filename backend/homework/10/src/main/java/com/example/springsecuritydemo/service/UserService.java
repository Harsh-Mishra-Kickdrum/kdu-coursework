package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of {@link UserDto} representing all users.
     */
    List<UserDto> getAllUsers();

    /**
     * Finds a user by their username.
     *
     * @param userName the username of the user to find.
     * @return an {@link Optional} of {@link UserDto} if the user is found, or an empty {@link Optional} if not.
     */
    Optional<UserDto> getUserByUserName(String userName);

    /**
     * Adds a new user to the system.
     *
     * @param userDto the user data transfer object containing information about the user to add, including the password.
     * @return the added {@link UserDto} with potentially additional data set (e.g., generated ID).
     */
    UserDto addUser(UserDto userDto);

    /**
     * Validates if the provided credentials match an existing user's credentials.
     *
     * @param userName the username to validate.
     * @param password the password to validate.
     * @return true if the credentials are valid, false otherwise.
     */
    boolean validateUserCredentials(String userName, String password);

    /**
     * Retrieves the role of a user by their username.
     *
     * @param username the username of the user whose role is to be retrieved.
     * @return a string representing the user's role.
     */
    String getUserRoleByUsername(String username);
}
