package com.example.springjpademo.service;

import com.example.springjpademo.entity.ShiftType;
import com.example.springjpademo.repository.ShiftTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ShiftTypeService {

    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * Saves a shift type entity to the database.
     *
     * @param shiftType The shift type entity to be saved.
     * @return The saved shift type entity.
     */
    @Transactional
    public ShiftType saveShiftType(ShiftType shiftType) {
        try {
            ShiftType savedShiftType = shiftTypeRepository.save(shiftType);
            log.info("Shift Type saved successfully: {}", savedShiftType.getUqName());
            return savedShiftType;
        } catch (Exception e) {
            log.error("Error saving shift type: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Retrieves all shift types from the database.
     *
     * @return A list of all shift type entities.
     */
    public List<ShiftType> getAllShiftTypes() {
        try {
            return shiftTypeRepository.findAll();
        } catch (Exception e) {
            log.error("Error retrieving all shift types: {}", e.getMessage(), e);
            throw e;
        }
    }
}
