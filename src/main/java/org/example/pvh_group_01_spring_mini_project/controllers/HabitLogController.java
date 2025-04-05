package org.example.pvh_group_01_spring_mini_project.controllers;



//This is after I have clone and write code from here
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/habit-logs")
public class HabitLogController {
    @GetMapping
    public HabitLog getHabitLog() {
        return null;
    }

    @PostMapping
    public HabitLog addHabitLog(@RequestBody HabitLog habitLog) {
        return null;
    }
}
