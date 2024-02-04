package com.example.springjdbcdemo.mapper;

import com.example.springjdbcdemo.exception.DataMappingException;
import com.example.springjdbcdemo.model.ShiftType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Maps a row from the ResultSet to a ShiftType object.
 */
public class ShiftTypeRowMapper implements RowMapper<ShiftType> {

    @Override
    public ShiftType mapRow(ResultSet rs, int rowNum) {
        try {
            ShiftType shiftType = new ShiftType();
            shiftType.setId(UUID.fromString(rs.getString("id")));
            shiftType.setUqName(rs.getString("uqName"));
            shiftType.setDescription(rs.getString("description"));
            shiftType.setActive(rs.getBoolean("active"));
            shiftType.setCreatedBy(rs.getString("createdBy"));
            shiftType.setUpdatedBy(rs.getString("updatedBy"));
            shiftType.setCreatedAt(rs.getTimestamp("createdAt"));
            shiftType.setUpdatedAt(rs.getTimestamp("updatedAt"));
            shiftType.setTimeZone(rs.getString("timeZone"));
            shiftType.setTenantId(UUID.fromString(rs.getString("tenantId")));
            return shiftType;
        } catch (SQLException e) {
            throw new DataMappingException("Error mapping ShiftType from ResultSet", e);
        }
    }
}
