package com.example.springjdbcdemo.dao;

import com.example.springjdbcdemo.dto.ShiftUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class ShiftUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    public void saveShiftUser(ShiftUserDto shiftUserDTO) {
        try {
            String sql = "INSERT INTO public.shift_users(id, shift_id, user_id, created_by, updated_by, created_at, updated_at, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    generateUUID(),
                    shiftUserDTO.getShiftId(),
                    shiftUserDTO.getUserId(),
                    shiftUserDTO.getCreatedBy(),
                    shiftUserDTO.getUpdatedBy(),
                    shiftUserDTO.getCreatedAt(),
                    shiftUserDTO.getUpdatedAt(),
                    shiftUserDTO.getTenantId());
            log.info("Successfully saved ShiftUser for Shift ID: {}", shiftUserDTO.getShiftId());
        } catch (Exception e) {
            log.error("Error saving ShiftUser: {}", e.getMessage(), e);
            throw e;
        }
    }
}
