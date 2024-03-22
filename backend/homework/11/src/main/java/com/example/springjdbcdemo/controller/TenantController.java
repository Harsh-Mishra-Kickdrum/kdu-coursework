package com.example.springjdbcdemo.controller;

import com.example.springjdbcdemo.dto.*;
import com.example.springjdbcdemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for handling tenant-specific operations.
 */
@RestController
@RequestMapping("/api")
public class TenantController {

    private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

    private final ShiftService shiftService;
    private final ShiftTypeService shiftTypeService;
    private final UserService userService;
    private final AllEntitiesService allEntitiesService;
    private final ShiftUserService shiftUserService;

    @Autowired
    public TenantController(ShiftService shiftService, ShiftTypeService shiftTypeService, UserService userService, AllEntitiesService allEntitiesService, ShiftUserService shiftUserService) {
        this.shiftService = shiftService;
        this.shiftTypeService = shiftTypeService;
        this.userService = userService;
        this.allEntitiesService = allEntitiesService;
        this.shiftUserService = shiftUserService;
    }

    @PostMapping("/saveShift")
    public ResponseEntity<Object> saveShift(@RequestBody ShiftDto shiftDto) {
        try {
            shiftService.saveShift(shiftDto);
            logger.info("Shift saved successfully");
            return ResponseEntity.ok("Shift saved successfully");
        } catch (DataAccessException e) {
            logger.error("Error saving Shift", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Shift: " + e.getMessage());
        }
    }

    @GetMapping("/getShift/{id}")
    public ResponseEntity<Object> getShift(@PathVariable("id")UUID tenantID) {
        try {
            List<ShiftDto> shiftDTO = shiftService.getShiftsByTenantId(tenantID);
            if (!shiftDTO.isEmpty()) {
                logger.info("Shifts retrieved successfully for tenant ID: {}", tenantID);
                return ResponseEntity.ok(shiftDTO);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (DataAccessException e) {
            logger.error("Error retrieving Shift for tenant ID: {}", tenantID, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving Shift: " + e.getMessage());
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<Object> saveAllEntities(@RequestBody AllEntitiesDTO allEntitiesDTO) {
        try {
            allEntitiesService.saveAllEntities(allEntitiesDTO);
            logger.info("All entities saved successfully");
            return ResponseEntity.ok("All entities saved successfully");
        } catch (DataAccessException e) {
            logger.error("Error saving entities", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving entities: " + e.getMessage());
        }
    }

    @PostMapping("/saveShiftType")
    public ResponseEntity<Object> saveShiftType(@RequestBody ShiftTypeDto shiftTypeDto) {
        try {
            shiftTypeService.saveShiftType(shiftTypeDto);
            logger.info("ShiftType saved successfully");
            return ResponseEntity.ok("ShiftType saved successfully");
        } catch (DataAccessException e) {
            logger.error("Error saving ShiftType", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving ShiftType: " + e.getMessage());
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            logger.info("User saved successfully");
            return ResponseEntity.ok("User saved successfully");
        } catch (DataAccessException e) {
            logger.error("Error saving User", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving User: " + e.getMessage());
        }
    }

    @PostMapping("/saveShiftUser")
    public ResponseEntity<Object> saveShiftUser(@RequestBody ShiftUserDto shiftUserDto) {
        try {
            shiftUserService.saveShiftUser(shiftUserDto);
            logger.info("ShiftUser saved successfully");
            return ResponseEntity.ok("ShiftUser saved successfully");
        } catch (DataAccessException e) {
            logger.error("Error saving ShiftUser", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving ShiftUser: " + e.getMessage());
        }
    }
}
