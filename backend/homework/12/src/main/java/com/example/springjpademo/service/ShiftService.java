package com.example.springjpademo.service;

import com.example.springjpademo.entity.Shift;
import com.example.springjpademo.repository.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ShiftService {

    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    /**
     * Saves a shift entity to the database.
     *
     * @param shift The shift entity to be saved.
     * @return The saved shift entity.
     */
    @Transactional
    public Shift saveShift(Shift shift) {
        try {
            Shift savedShift = shiftRepository.save(shift);
            log.info("Shift saved successfully: {}", savedShift.getName());
            return savedShift;
        } catch (Exception e) {
            log.error("Error saving shift: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Retrieves all shifts from the database.
     *
     * @return A list of all shift entities.
     */
    public List<Shift> getAllShifts() {
        try {
            return shiftRepository.findAll();
        } catch (Exception e) {
            log.error("Error retrieving all shifts: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Retrieves the top shifts within a given start and end date, ordered by name.
     *
     * @param startDate The start date of the shift.
     * @param endDate The end date of the shift.
     * @return A list of shift entities within the specified date range.
     */
    public List<Shift> findTopShifts(LocalDate startDate, LocalDate endDate) {
        try {
            return shiftRepository.findTopThree(startDate, endDate);
        } catch (Exception e) {
            log.error("Error retrieving top shifts: {}", e.getMessage(), e);
            throw e;
        }
    }
}
