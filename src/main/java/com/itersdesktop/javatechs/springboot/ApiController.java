package com.itersdesktop.javatechs.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ApiController {
    @GetMapping("/api/v1.0/hello")
    public String hello() {
        return "Hello World REST API with Spring Boot. The current time is: " + new Date();
    }

    @GetMapping("/api/v1.0/hw")
    public Response hw() {
        return new Response("Hello World REST API with Spring Boot.");
    }
}
