package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling administrative actions related to user management.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;

    /**
     * Constructor for dependency injection of UserService.
     * @param userService The service to manage user data.
     */
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves and displays all users in the system.
     * @return A response entity containing a list of UserDto objects and the HTTP status.
     */
        @GetMapping("/displayAllUsers")
    public ResponseEntity<List<UserDto>> displayAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers(); // Assuming getAllUsers returns List<UserDto>
            logger.info("Retrieved {} user(s) from the database", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching users: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a new user to the system.
     * @param userDto Data transfer object containing user details.
     * @return A response entity with a success message or error information and the appropriate HTTP status.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        try {
            userService.addUser(userDto);
            logger.info("User {} added successfully", userDto.getUserName());
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while adding user {}: {}", userDto.getUserName(), e.getMessage(), e);
            return new ResponseEntity<>("Unable to add user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetches a user by their username.
     * @param username The username of the user to retrieve.
     * @return A response entity containing the UserDto object if found, otherwise a NOT_FOUND status.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUserName(username)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
