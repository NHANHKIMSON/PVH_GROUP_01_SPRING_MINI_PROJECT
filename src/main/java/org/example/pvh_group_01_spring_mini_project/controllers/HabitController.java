package org.example.pvh_group_01_spring_mini_project.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habits")
@SecurityRequirement(name = "bearerAuth")
public class HabitController {


    @GetMapping
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/{habit}")
    public String habit(){
        return "Habit";
    }

}