package org.example.pvh_group_01_spring_mini_project.controllers;



//This is after I have clone and write code from here
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/habit-logs")
public class HabitLogController {

    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @GetMapping
    public HabitLog getHabitLog() {
        return null;
    }

    @PostMapping
    public HabitLog addHabitLog(@RequestBody HabitLog habitLog) {
        return null;
    }
}
