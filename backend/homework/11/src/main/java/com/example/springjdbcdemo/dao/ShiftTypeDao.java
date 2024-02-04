package com.example.springjdbcdemo.dao;

import com.example.springjdbcdemo.dto.ShiftTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class ShiftTypeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Generates a new UUID.
     *
     * @return A randomly generated UUID.
     */
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * Saves a ShiftType to the database.
     *
     * @param shiftTypeDTO The ShiftType data transfer object.
     */
    public void saveShiftType(ShiftTypeDto shiftTypeDTO) {
        try {
            String sql = "INSERT INTO public.shift_types(id, uq_name, description, active, created_by, updated_by, created_at, updated_at, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    generateUUID(),
                    shiftTypeDTO.getUqName(),
                    shiftTypeDTO.getDescription(),
                    shiftTypeDTO.isActive(),
                    shiftTypeDTO.getCreatedBy(),
                    shiftTypeDTO.getUpdatedBy(),
                    shiftTypeDTO.getCreatedAt(),
                    shiftTypeDTO.getUpdatedAt(),
                    shiftTypeDTO.getTimeZone(),
                    shiftTypeDTO.getTenantId());
            log.info("Successfully saved ShiftType: {}", shiftTypeDTO.getUqName());
        } catch (Exception e) {
            log.error("Error saving ShiftType: {}", e.getMessage(), e);
            throw e;
        }
    }
}
