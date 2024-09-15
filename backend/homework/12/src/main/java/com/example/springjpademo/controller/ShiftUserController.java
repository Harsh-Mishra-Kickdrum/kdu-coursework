package com.example.springjpademo.controller;

import com.example.springjpademo.entity.ShiftUser;
import com.example.springjpademo.exception.UserNotFoundException;
import com.example.springjpademo.service.ShiftUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/shift-users")
public class ShiftUserController {

    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     * Creates and saves a new ShiftUser entity.
     *
     * @param shiftUser The ShiftUser entity to be saved.
     * @return The saved ShiftUser entity.
     */
    @PostMapping("/save")
    public ResponseEntity<ShiftUser> createShiftUser(@RequestBody ShiftUser shiftUser) {
        try {
            ShiftUser savedShiftUser = shiftUserService.saveShiftUser(shiftUser);
            log.info("Shift User saved successfully: {}", savedShiftUser.getId());
            return ResponseEntity.ok(savedShiftUser);
        } catch (Exception e) {
            log.error("Error saving Shift User: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }


    /**
     * Deletes a ShiftUser entity by its ID.
     *
     * @param userId The ID of the ShiftUser to delete.
     * @return A ResponseEntity with a message indicating success or failure.
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID userId) {
        try {
            shiftUserService.deleteShiftUser(userId);
            log.info("Deleted Shift User with ID {}", userId);
            return ResponseEntity.ok("Deleted Shift User with ID " + userId);
        } catch (UserNotFoundException e) {
            log.error("Error deleting Shift User: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shift User not found with ID: " + userId);
        } catch (Exception e) {
            log.error("Error during deleting Shift User: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("An error occurred while deleting Shift User with ID: " + userId);
        }
    }
}
