package com.example.dataBaseTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DataBaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataBaseTestApplication.class, args);
    }

}
