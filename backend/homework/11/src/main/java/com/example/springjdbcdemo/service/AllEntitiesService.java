package com.example.springjdbcdemo.service;

import com.example.springjdbcdemo.dto.AllEntitiesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AllEntitiesService {

    private final ShiftTypeService shiftTypeService;
    private final ShiftService shiftService;
    private final UserService userService;
    private final ShiftUserService shiftUserService;

    @Autowired
    public AllEntitiesService(
            ShiftTypeService shiftTypeService,
            ShiftService shiftService,
            UserService userService,
            ShiftUserService shiftUserService) {
        this.shiftTypeService = shiftTypeService;
        this.shiftService = shiftService;
        this.userService = userService;
        this.shiftUserService = shiftUserService;
    }

    /**
     * Saves all entities related to a shift operation in a transactional manner.
     *
     * @param allEntitiesDTO The DTO containing all entities to be saved.
     */
    @Transactional
    public void saveAllEntities(AllEntitiesDTO allEntitiesDTO) {
        try {
            shiftTypeService.saveShiftType(allEntitiesDTO.getShiftTypeDto());
            shiftService.saveShift(allEntitiesDTO.getShiftDto());
            userService.saveUser(allEntitiesDTO.getUserDto());
            shiftUserService.saveShiftUser(allEntitiesDTO.getShiftUserDto());
            log.info("All entities saved successfully");
        } catch (Exception e) {
            log.error("Error saving all entities: {}", e.getMessage(), e);
            // Transaction will automatically roll back due to the exception
            throw e;
        }
    }
}
