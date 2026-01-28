package com.example.cicd.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello CI/CD 🚀";
    }
}
