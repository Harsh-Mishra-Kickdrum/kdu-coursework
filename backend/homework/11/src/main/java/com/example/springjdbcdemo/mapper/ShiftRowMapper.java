package com.example.springjdbcdemo.mapper;

import com.example.springjdbcdemo.exception.DataMappingException;
import com.example.springjdbcdemo.model.Shift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * Maps a row from the ResultSet to a Shift object.
 */
public class ShiftRowMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Shift shift = new Shift();
            shift.setId(UUID.fromString(rs.getString("id")));
            shift.setShiftTypeId(UUID.fromString(rs.getString("shiftTypeId")));
            shift.setName(rs.getString("name"));
            shift.setDateStart(new Date(rs.getTimestamp("dateStart").getTime()));
            shift.setDateEnd(new Date(rs.getTimestamp("dateEnd").getTime()));
            shift.setTimeStart(rs.getTime("timeStart"));
            shift.setTimeEnd(rs.getTime("timeEnd"));
            shift.setCreatedAt(rs.getTimestamp("createdAt"));
            shift.setCreatedBy(rs.getString("createdBy"));
            shift.setUpdatedBy(rs.getString("updatedBy"));
            shift.setUpdatedAt(rs.getTimestamp("updatedAt"));
            shift.setTimeZone(rs.getString("timeZone"));
            shift.setTenantId(UUID.fromString(rs.getString("tenantId")));
            return shift;
        }  catch (SQLException e) {
            throw new DataMappingException("Error mapping Shift from ResultSet", e);
        }
    }
}

