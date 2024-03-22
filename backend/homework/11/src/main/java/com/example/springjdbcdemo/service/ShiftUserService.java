package com.example.springjdbcdemo.service;

import com.example.springjdbcdemo.dao.ShiftUserDao;
import com.example.springjdbcdemo.dto.ShiftUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShiftUserService {

    private final ShiftUserDao shiftUserDao;

    @Autowired
    public ShiftUserService(ShiftUserDao shiftUserDao) {
        this.shiftUserDao = shiftUserDao;
    }

    /**
     * Saves a shift-user association to the database.
     *
     * @param shiftUserDto The shift-user data transfer object.
     */
    public void saveShiftUser(ShiftUserDto shiftUserDto) {
        try {
            shiftUserDao.saveShiftUser(shiftUserDto);
            log.info("ShiftUser association saved successfully for Shift ID: {}", shiftUserDto.getShiftId());
        } catch (Exception e) {
            log.error("Error saving shift-user association: {}", e.getMessage(), e);
            throw e;
        }
    }
}
