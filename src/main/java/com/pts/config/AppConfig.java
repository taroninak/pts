package com.pts.config;

import com.pts.helper.SimplePreparedStatmentCreator;
import com.pts.model.ContactRowMapper;
import com.pts.model.ProjectRowMapper;
import com.pts.service.ContactService;
import com.pts.service.ManipulationService;
import com.pts.service.ProjectService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.util.Random;

@Configuration
//@ComponentScan(basePackages = "com.pts",excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pts\\.initializer\\.*"))
public class AppConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new DriverManagerDataSource("jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=UTC", "root", "password"));
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/PTS?useSSL=false&serverTimezone=UTC", "root", "password");
    }

    @Bean
    public GeneratedKeyHolder generatedKeyHolder() {
        return new GeneratedKeyHolder();
    }

    @Bean
    public SimplePreparedStatmentCreator simplePreparedStatmentCreator() {
        return new SimplePreparedStatmentCreator();
    }

    @Bean
    public ProjectService dbService() {
        return new ProjectService();
    }

    @Bean
    public ContactService contactService() {
        return new ContactService();
    }

    @Bean
    public ManipulationService dbManipulationService() {
        return new ManipulationService();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public ProjectRowMapper projectRowMapper() {
        return new ProjectRowMapper();
    }

    @Bean
    public ContactRowMapper contactRowMapper() {
        return new ContactRowMapper();
    }
}