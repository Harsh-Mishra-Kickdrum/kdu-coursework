package com.example.springjpademo.controller;

import com.example.springjpademo.entity.Shift;
import com.example.springjpademo.service.ShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shifts")
@Slf4j
public class ShiftController {

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * Saves a shift to the database.
     * @param shift The shift entity to be saved.
     * @return The saved shift entity.
     */
    @PostMapping("/saveShift")
    public ResponseEntity<Shift> saveShift(@RequestBody Shift shift) {
        try {
            Shift savedShift = shiftService.saveShift(shift);
            log.info("Shift saved successfully: {}", savedShift.getId());
            return ResponseEntity.ok(savedShift);
        } catch (Exception e) {
            log.error("Error saving shift: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves the top shifts within a given date range.
     * @return A list of top shifts.
     */
    @GetMapping("/topThreeUsers")
    public ResponseEntity<List<Shift>> getTopShifts() {
        try {
            LocalDate startDate = LocalDate.of(2024, 1, 3); // Example start date
            LocalDate endDate = LocalDate.of(2024, 2, 28); // Example end date
            List<Shift> topShifts = shiftService.findTopShifts(startDate, endDate);
            return ResponseEntity.ok(topShifts);
        } catch (Exception e) {
            log.error("Error retrieving top shifts: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
