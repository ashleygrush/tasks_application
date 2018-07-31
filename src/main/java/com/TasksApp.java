package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJpaAuditing

@EnableCaching
public class TasksApp {

    public static void main(String[] args) {
        SpringApplication.run(TasksApp.class, args);
    }

}
