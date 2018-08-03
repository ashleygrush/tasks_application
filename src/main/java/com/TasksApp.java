package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJpaAuditing

@EnableCaching
public class TasksApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TasksApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TasksApp.class);

    }
}

// ************* TO RUN LOCALLY ******************

// to run locally again,
// comment out database location in application.properties; added "provided" dependency in pom file and  extends SpringBootServleetInit. and Override above.