package com.pts.service;

import com.pts.config.AppConfig;
import com.pts.config.AppConstants;
import com.pts.model.Contact;
import com.pts.model.Project;
import com.pts.model.ProjectStatus;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by taronpetrosyan on 8/16/16.
 */
@Component
public class ManipulationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dbName")
    private String dbName;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Random random;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContactService contactService;

    public void createPtsDb() {
        String use = "USE " + dbName;
        try {
            jdbcTemplate.execute(use);
        } catch (Exception ex) {
            String sql = "CREATE DATABASE " + dbName;
            jdbcTemplate.execute(sql);
        } finally {
            jdbcTemplate.setDataSource(dataSource);
        }

    }

    public void createProjectsTable() {
        String query = "SHOW TABLES LIKE 'Projects'";
        List<String> projects = jdbcTemplate.queryForList(query, String.class);
        if (projects.isEmpty()) {
            String sql = "CREATE TABLE Projects ( id MEDIUMINT NOT NULL AUTO_INCREMENT, title CHAR(30) NOT NULL, status ENUM('NEW','ACTIVE', 'ONGOING', 'CLOSED'), PRIMARY KEY (id))";
            jdbcTemplate.execute(sql);
        }

    }

    public void createContactsTable() {
        String query = "SHOW TABLES LIKE 'Contacts'";
        List<String> projects = jdbcTemplate.queryForList(query, String.class);
        if (projects.isEmpty()) {
            String sql = "CREATE TABLE Contacts ( id MEDIUMINT NOT NULL AUTO_INCREMENT, project_id MEDIUMINT, contact CHAR(30) NOT NULL, email CHAR(30), phone CHAR(30), PRIMARY KEY (id), FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE ON UPDATE CASCADE)";
            jdbcTemplate.execute(sql);
        }

    }

    public void fillProjectsTable() {
        ProjectStatus[] statuses = new ProjectStatus[]{ ProjectStatus.NEW, ProjectStatus.ACTIVE, ProjectStatus.ONGOING, ProjectStatus.CLOSED };
        int projectsCount = AppConstants.MIN_PROJECTS_COUNT + random.nextInt(AppConstants.MAX_PROJECTS_COUNT - AppConstants.MIN_PROJECTS_COUNT);
        for (int i = 0; i < projectsCount; i++) {
            projectService.addProject(new Project(null, "Project" + (i + 1), statuses[random.nextInt(statuses.length)]));
        }
    }

    public void fillContactsTable() {
        List<Project> projects = projectService.listProjects();
        for (Project project : projects) {
            int contactsCount = AppConstants.MIN_CONTACTS_COUNT_PER_PROJECT + random.nextInt(AppConstants.MAX_CONTACTS_COUNT_PER_PROJECT - AppConstants.MIN_CONTACTS_COUNT_PER_PROJECT);
            for (int i = 0; i < contactsCount; i++) {
                contactService.addContact(new Contact(null, project.getId(), "Contact" + (i + 1), "contact" + (i + 1) + "@" + project.getTitle() + ".com"));
            }
        }
    }
}
