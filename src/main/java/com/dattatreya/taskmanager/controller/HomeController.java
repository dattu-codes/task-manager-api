package com.dattatreya.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Task Manager API is running. Use POST /auth/register, POST /auth/login, then /tasks with a Bearer token.";
    }
}
