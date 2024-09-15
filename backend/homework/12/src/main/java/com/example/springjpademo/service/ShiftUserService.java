package com.example.springjpademo.service;

import com.example.springjpademo.entity.ShiftUser;
import com.example.springjpademo.repository.ShiftUserRepository;
import com.example.springjpademo.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ShiftUserService {

    private final ShiftUserRepository shiftUserRepository;

    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    /**
     * Saves a shift-user association to the database.
     *
     * @param shiftUser The shift-user entity.
     * @return The saved ShiftUser entity.
     */
    public ShiftUser saveShiftUser(ShiftUser shiftUser) {
        try {
            ShiftUser savedShiftUser = shiftUserRepository.save(shiftUser);
            log.info("ShiftUser association saved successfully for Shift ID: {}", savedShiftUser.getShift().getId());
            return savedShiftUser;
        } catch (Exception e) {
            log.error("Error saving shift-user association: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Deletes a shift-user association based on the user's ID.
     *
     * @param userId The UUID of the user.
     */
    public void deleteShiftUser(UUID userId) {
        try {
            shiftUserRepository.deleteByUserId(userId);
            log.info("ShiftUser association deleted successfully for User ID: {}", userId);
        } catch (Exception e) {
            log.error("Error deleting shift-user association for User ID: {}", userId, e);
            throw new UserNotFoundException("Shift-user association not found for User ID: " + userId);
        }
    }
}
