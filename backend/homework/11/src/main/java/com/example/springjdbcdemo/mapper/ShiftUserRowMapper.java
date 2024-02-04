package com.example.springjdbcdemo.mapper;

import com.example.springjdbcdemo.exception.DataMappingException;
import com.example.springjdbcdemo.model.ShiftUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Maps a row from the ResultSet to a ShiftUser object.
 */
public class ShiftUserRowMapper implements RowMapper<ShiftUser> {

    @Override
    public ShiftUser mapRow(ResultSet rs, int rowNum) {
        try {
            ShiftUser shiftUser = new ShiftUser();
            shiftUser.setId(UUID.fromString(rs.getString("id")));
            shiftUser.setShiftId(UUID.fromString(rs.getString("shiftId")));
            shiftUser.setUserId(UUID.fromString(rs.getString("userId")));
            shiftUser.setCreatedBy(rs.getString("createdBy"));
            shiftUser.setUpdatedBy(rs.getString("updatedBy"));
            shiftUser.setCreatedAt(rs.getTimestamp("createdAt"));
            shiftUser.setUpdatedAt(rs.getTimestamp("updatedAt"));
            shiftUser.setTenantId(UUID.fromString(rs.getString("tenantId")));
            return shiftUser;
        } catch (SQLException e) {

            throw new DataMappingException("Error mapping ShiftUser from ResultSet", e);
        }
    }
}
