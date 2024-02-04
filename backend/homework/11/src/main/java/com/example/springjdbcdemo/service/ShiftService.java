package com.example.springjdbcdemo.service;

import com.example.springjdbcdemo.dao.ShiftDao;
import com.example.springjdbcdemo.dto.ShiftDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ShiftService {

    private final ShiftDao shiftDao;

    @Autowired
    public ShiftService(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    /**
     * Saves a shift to the database.
     *
     * @param shiftDto The shift data transfer object.
     */
    public void saveShift(ShiftDto shiftDto) {
        try {
            shiftDao.saveShift(shiftDto);
            log.info("Shift saved successfully: {}", shiftDto.getName());
        } catch (Exception e) {
            log.error("Error saving shift: {}", e.getMessage(), e);
            throw e;
        }
    }


    /**
     * Retrieves a list of shifts for a given tenant ID.
     *
     * @param tenantID The UUID of the tenant.
     * @return A list of ShiftDto objects.
     */
    public List<ShiftDto> getShiftsByTenantId(UUID tenantID) {
        try {
            return shiftDao.getShift(tenantID);
        } catch (Exception e) {
            log.error("Error retrieving shifts: {}", e.getMessage(), e);
            throw e;
        }
    }
}
