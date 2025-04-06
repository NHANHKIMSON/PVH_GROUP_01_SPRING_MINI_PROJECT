package org.example.pvh_group_01_spring_mini_project.controllers;



//This is after I have clone and write code from here
import jakarta.validation.Valid;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
public class HabitLogController {

    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @PostMapping
    public ResponseEntity<ApiRespones<HabitLog>> addAttendee(@RequestBody HabitLogRequest habitLogRequest ) {
        HabitLog addHabitLog = habitLogService.addHabitLog(habitLogRequest);
        ApiRespones<HabitLog> response = ApiRespones.<HabitLog>builder()
                .message("Habit Log added successfully")
                .status(HttpStatus.CREATED)
                .payload(addHabitLog)
                .success(true)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiRespones<List<HabitLog>>> getAllHabitLogs(@Valid @RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size,@PathVariable("habit-id") UUID habitLogId) {
        int offset = (page - 1) * size;
        List<HabitLog> getHabitLog = habitLogService.getAllHabitLog(offset, size, habitLogId);
        ApiRespones<List<HabitLog>> response = ApiRespones.<List<HabitLog>>builder()
                .message("Habit Log get by id successfully")
                .status(HttpStatus.CREATED)
                .payload(getHabitLog)
                .success(true)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}