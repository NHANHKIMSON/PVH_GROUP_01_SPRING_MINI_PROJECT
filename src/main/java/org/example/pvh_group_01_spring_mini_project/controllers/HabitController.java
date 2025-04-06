package org.example.pvh_group_01_spring_mini_project.controllers;

import jakarta.validation.Valid;
import org.example.pvh_group_01_spring_mini_project.exception.NotFoundException;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiResponceDelete;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<ApiRespones<List<Habit>>> getAllHabits(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        ApiRespones<List<Habit>> apiRespones = ApiRespones.<List<Habit>>builder()
                .success(true)
                .message("Fetched all habits successfully")
                .status(HttpStatus.OK)
                .payload(habitService.getAllHabits(page, size))
                .timestamps(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiRespones, HttpStatus.OK);

    }

    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiRespones<Habit>> getHabitById(@PathVariable("habit-id") UUID habitId)  {
        ApiRespones<Habit> apiRespones = ApiRespones.<Habit>builder()
                .success(true)
                .message("Habit fetched successfully!!!")
                .status(HttpStatus.OK)
                .payload(habitService.getHabitById(habitId))
                .timestamps(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiRespones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiRespones<Habit>> createHabit(@Valid @RequestBody HabitRequest habitRequest) {
        ApiRespones<Habit> apiRespones = ApiRespones.<Habit>builder()
                .success(true)
                .message("Habit created successfully!!!")
                .status(HttpStatus.CREATED)
                .payload(habitService.createHabit(habitRequest))
                .timestamps(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiRespones, HttpStatus.OK);
    }

    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponceDelete<Habit>> deleteHabit(@PathVariable("habit-id") UUID id) {
        Habit habit = habitService.deleteHabit(id);

        return new ResponseEntity<>(new ApiResponceDelete<>(habit), HttpStatus.OK);
    }

    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiRespones<Habit>> updateHabit(@PathVariable("habit-id") UUID id, @Valid @RequestBody HabitRequest habitRequest) {
        ApiRespones<Habit> response = ApiRespones.<Habit>builder()
                .success(true)
                .message("Habit successfully Updated")
                .status(HttpStatus.OK)
                .payload(habitService.updateHabit(id, habitRequest))
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}