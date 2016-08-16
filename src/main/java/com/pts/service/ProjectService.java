package com.pts.service;

import com.pts.model.Project;
import com.pts.model.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
@Component
public class ProjectService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProjectRowMapper projectRowMapper;

    public List<Project> listProjects() {
        String select = "SELECT * FROM Projects";
        return jdbcTemplate.query(select, projectRowMapper);
    }

    public Project getProjectById(int id) {
        String select = "SELECT * FROM Projects WHERE id=" + id;
        return jdbcTemplate.query(select, projectRowMapper).get(0);
    }

    public void addProject(Project project) {
        String insert = "INSERT INTO Projects (title, status) VALUES(?, ?)";
        jdbcTemplate.update(insert, project.getTitle(), project.getStatus().toString());
    }

    public void editProject(Project project) {
        String update = "UPDATE Projects SET (title=?, status=?) WHERE id=?";
        jdbcTemplate.update(update, project.getTitle(), project.getStatus().toString(), project.getId());
    }

    public void deleteProject(int id) {
        String delete = "DELETE FROM Projects WHERE id=" + id;
        jdbcTemplate.execute(delete);
    }
}
