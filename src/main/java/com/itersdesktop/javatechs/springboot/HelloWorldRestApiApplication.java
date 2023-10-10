package com.itersdesktop.javatechs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.itersdesktop.javatechs.springboot"})
@EnableAsync
public class HelloWorldRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldRestApiApplication.class, args);
    }
}
