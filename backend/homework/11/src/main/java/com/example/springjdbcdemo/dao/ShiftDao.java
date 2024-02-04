package com.example.springjdbcdemo.dao;

import com.example.springjdbcdemo.dto.ShiftDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for dependency injection of JdbcTemplate
     *
     * @param jdbcTemplate the JdbcTemplate object for database interaction
     */
    @Autowired
    public ShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Generates a new UUID.
     *
     * @return A new UUID object
     */
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * Saves a new shift into the database.
     *
     * @param shiftDTO the ShiftDto object containing shift data
     */
    public void saveShift(ShiftDto shiftDTO) {
        try {
            String sql = "INSERT INTO public.shifts(id, shift_type_id, name, date_start, date_end, time_start, time_end, created_by, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    generateUUID(),
                    shiftDTO.getShiftTypeId(),
                    shiftDTO.getName(),
                    shiftDTO.getDateStart(),
                    shiftDTO.getDateEnd(),
                    shiftDTO.getTimeStart(),
                    shiftDTO.getTimeEnd(),
                    shiftDTO.getCreatedBy(),
                    shiftDTO.getUpdatedBy(),
                    shiftDTO.getTimeZone(),
                    shiftDTO.getTenantId());
            log.info("Successfully saved Shift: {}", shiftDTO.getName());
        } catch (Exception e) {
            log.error("Error saving Shift: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Retrieves a list of shifts based on the tenant ID.
     *
     * @param tenantID the UUID of the tenant
     * @return A list of ShiftDto objects
     */
    public List<ShiftDto> getShift(UUID tenantID) {
        try {
            String sql = "SELECT * FROM shifts WHERE tenant_id = ?";
            return jdbcTemplate.query(sql, new Object[]{tenantID}, this::mapRowToShiftDto);
        } catch (Exception e) {
            log.error("An exception occurred in getShift: ", e);
            throw e;
        }
    }

    /**
     * Maps a SQL result set to a ShiftDto object.
     * This is a helper method used by getShift.
     *
     * @param rs the ResultSet from the SQL query
     * @param rowNum the number of the current row
     * @return A ShiftDto object populated with data from the current row
     * @throws SQLException if there is a problem accessing data from the ResultSet
     */
    private ShiftDto mapRowToShiftDto(ResultSet rs, int rowNum) throws SQLException {
        return new ShiftDto(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("shift_type_id"),
                rs.getString("name"),
                rs.getDate("date_start"),
                rs.getDate("date_end"),
                rs.getTime("time_start"),
                rs.getTime("time_end"),
                rs.getString("created_by"),
                rs.getString("updated_by"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"),
                rs.getString("time_zone"),
                (UUID) rs.getObject("tenant_id")
        );
    }
}
