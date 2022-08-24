package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("add localhost");
        System.out.println("add localhost");
        System.out.println("add localhost");
        System.out.println("################################## Starting application ##################################");
        SpringApplication.run(Application.class, args);
    }

}
