package com.pts.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by taronpetrosyan on 8/16/16.
 */
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int projectId = resultSet.getInt("project_id");
        String contact = resultSet.getString("contact");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");

        return new Contact(id, projectId, contact, email, phone);
    }
}
