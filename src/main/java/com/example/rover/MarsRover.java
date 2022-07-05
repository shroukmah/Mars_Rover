package com.example.rover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class MarsRover {

    public static void main(String[] args) {
        SpringApplication.run(MarsRover.class, args);
    }

}
