package com.pts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pts.helper.SimplePreparedStatementCreator;
import com.pts.service.ContactService;
import com.pts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.pts")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment env;

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
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
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ProjectService dbService() {
        return new ProjectService();
    }

    @Bean
    public ContactService contactService() {
        return new ContactService();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("resources/");
    }
}