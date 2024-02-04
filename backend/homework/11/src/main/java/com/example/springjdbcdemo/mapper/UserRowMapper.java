package com.example.springjdbcdemo.mapper;

import com.example.springjdbcdemo.exception.DataMappingException;
import com.example.springjdbcdemo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Maps a row from the ResultSet to a User object.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) {
        try {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            user.setLoggedIn(rs.getInt("loggedIn"));
            user.setTimeZone(rs.getString("timeZone"));
            user.setCreatedBy(rs.getString("createdBy"));
            user.setUpdatedBy(rs.getString("updatedBy"));
            user.setCreatedAt(rs.getTimestamp("createdAt"));
            user.setUpdatedAt(rs.getTimestamp("updatedAt"));
            user.setTenantId(UUID.fromString(rs.getString("tenantId")));
            return user;
        } catch (SQLException e) {

            throw new DataMappingException("Error mapping User from ResultSet", e);
        }
    }
}
