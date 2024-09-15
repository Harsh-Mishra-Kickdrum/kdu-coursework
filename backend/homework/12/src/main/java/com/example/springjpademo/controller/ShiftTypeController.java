package com.example.springjpademo.controller;

import com.example.springjpademo.entity.ShiftType;
import com.example.springjpademo.service.ShiftTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shift-types")
@Slf4j
public class ShiftTypeController {

    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     * Saves a new shift type to the database.
     *
     * @param shiftType The shift type entity to be saved.
     * @return The saved shift type entity wrapped in a ResponseEntity.
     */
    @PostMapping("/save")
    public ResponseEntity<ShiftType> saveShiftType(@RequestBody ShiftType shiftType) {
        try {
            ShiftType savedShiftType = shiftTypeService.saveShiftType(shiftType);
            log.info("ShiftType saved successfully: {}", savedShiftType.getUqName());
            return ResponseEntity.ok(savedShiftType);
        } catch (Exception e) {
            log.error("Error saving shift type: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
