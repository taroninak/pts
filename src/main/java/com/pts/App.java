package com.pts;

import com.pts.config.AppConfig;
import com.pts.service.ManipulationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by taronpetrosyan on 8/13/16.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        ManipulationService manipulationService = context.getBean(ManipulationService.class);
        manipulationService.createPtsDb();
        manipulationService.createProjectsTable();
        manipulationService.createContactsTable();
        manipulationService.fillProjectsTable();
        manipulationService.fillContactsTable();
    }
}
