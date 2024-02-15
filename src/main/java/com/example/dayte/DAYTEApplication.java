package com.example.dayte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DAYTEApplication {

    public static void main(String[] args) {
        SpringApplication.run(DAYTEApplication.class, args);
    }

}
