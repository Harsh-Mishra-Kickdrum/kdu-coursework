package com.example.springjpademo.controller;

import com.example.springjpademo.dto.UserDto;
import com.example.springjpademo.entity.User;
import com.example.springjpademo.exception.UserNotFoundException;
import com.example.springjpademo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates and saves a new User entity.
     *
     * @param user The User entity to be saved.
     * @return The saved User entity.
     */
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            log.info("User saved successfully: {}", user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Finds all users with pagination.
     *
     * @param page The page number to retrieve.
     * @param size The size of the page.
     * @return A page of User entities.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<User>> findAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        page = Math.max(page, 0);
        size = Math.min(Math.max(size, 1), 100); // Limiting max size to 100 for performance reasons

        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Updates an existing User entity identified by the given UUID.
     *
     * @param userId  The UUID of the User entity to update.
     * @param userDto The updated User information.
     * @return A ResponseEntity with a status message.
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody UserDto userDto) {
        try {
            userService.updateUserDetails(userId, userDto);
            log.info("User with ID {} updated successfully", userId);
            return ResponseEntity.ok("User updated successfully");
        } catch (UserNotFoundException e) {
            log.error("Error updating user: User not found with ID {}", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("An error occurred while updating the user");
        }
    }
}
