package com.rodion.telenor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rodion.telenor")
public class TelenorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelenorApplication.class, args);
    }
}
