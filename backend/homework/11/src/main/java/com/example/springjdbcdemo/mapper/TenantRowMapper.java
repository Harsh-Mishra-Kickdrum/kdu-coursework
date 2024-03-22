package com.example.springjdbcdemo.mapper;

import com.example.springjdbcdemo.exception.DataMappingException;
import com.example.springjdbcdemo.model.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Maps a row from the ResultSet to a Tenant object.
 */
public class TenantRowMapper implements RowMapper<Tenant> {

    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) {
        try {
            Tenant tenant = new Tenant();
            tenant.setId(UUID.fromString(rs.getString("id")));
            tenant.setCreatedBy(rs.getString("createdBy"));
            tenant.setUpdatedBy(rs.getString("updatedBy"));
            tenant.setCreatedAt(rs.getTimestamp("createdAt"));
            tenant.setUpdatedAt(rs.getTimestamp("updatedAt"));
            return tenant;
        } catch (SQLException e) {
            throw new DataMappingException("Error mapping Tenant from ResultSet", e);
        }
    }
}
