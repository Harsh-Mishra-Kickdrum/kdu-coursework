package com.example.springjdbcdemo.service;

import com.example.springjdbcdemo.dao.ShiftTypeDao;
import com.example.springjdbcdemo.dto.ShiftTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShiftTypeService {

    private final ShiftTypeDao shiftTypeDao;

    @Autowired
    public ShiftTypeService(ShiftTypeDao shiftTypeDao) {
        this.shiftTypeDao = shiftTypeDao;
    }

    /**
     * Saves a shift type to the database.
     *
     * @param shiftTypeDto The shift type data transfer object.
     */
    public void saveShiftType(ShiftTypeDto shiftTypeDto) {
        try {
            shiftTypeDao.saveShiftType(shiftTypeDto);
            log.info("Shift Type saved successfully: {}", shiftTypeDto.getUqName());
        } catch (Exception e) {
            log.error("Error saving shift type: {}", e.getMessage(), e);
            throw e;
        }
    }
}
