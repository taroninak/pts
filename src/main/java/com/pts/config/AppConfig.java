package com.pts.config;

import com.pts.helper.SimplePreparedStatementCreator;
import com.pts.model.ContactRowMapper;
import com.pts.model.ProjectRowMapper;
import com.pts.service.ContactService;
import com.pts.service.ManipulationService;
import com.pts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.util.Random;

@Configuration
//@ComponentScan(basePackages = "com.pts",excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pts\\.initializer\\.*"))
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new DriverManagerDataSource("jdbc:mysql://" + dbHost() + ":" + dbPort() + "?useSSL=false&serverTimezone=UTC", dbUsername(), dbPassword()));
        //"jdbc:mysql://sql9.freemysqlhosting.net:3306?useSSL=false&serverTimezone=UTC", "sql9132588", "HIPnC8tJXM"));
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql://" + dbHost() + ":" + dbPort() + "/" + dbName() + "?useSSL=false&serverTimezone=UTC", dbUsername(), dbPassword());
    }

    @Bean
    public String dbName() {
        return env.getRequiredProperty("DBNAME", String.class);
    }

    @Bean
    public String dbUsername() {
        return env.getRequiredProperty("DBUSERNAME", String.class);
    }

    @Bean
    public  String dbPassword() {
        return env.getRequiredProperty("DBPASSWORD", String.class);
    }

    @Bean
    public String dbHost() {
        return env.getRequiredProperty("DBHOST", String.class);
    }

    @Bean
    public String dbPort() {
        return env.getRequiredProperty("DBPORT", String.class);
    }

    @Bean
    public GeneratedKeyHolder generatedKeyHolder() {
        return new GeneratedKeyHolder();
    }

    @Bean
    public SimplePreparedStatementCreator simplePreparedStatementCreator() {
        return new SimplePreparedStatementCreator();
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