package org.example.pvh_group_01_spring_mini_project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auths")
public class AuthController {


    @GetMapping
    public String auth() {
        return "Hello World!";
    }
}
