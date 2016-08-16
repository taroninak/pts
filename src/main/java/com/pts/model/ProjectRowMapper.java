package com.pts.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by taronpetrosyan on 8/16/16.
 */
public class ProjectRowMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        ProjectStatus status = ProjectStatus.valueOf(resultSet.getString("status"));
        return new Project(id,title, status);
    }
}
