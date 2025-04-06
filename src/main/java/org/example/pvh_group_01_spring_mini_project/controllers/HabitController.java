package org.example.pvh_group_01_spring_mini_project.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponceDelete<Habit>> deleteHabit(@PathVariable UUID id) {
        Habit habit = habitService.deleteHabit(id);
        if (habit == null) {
            throw new NotFoundException("Habit Id " + id + "Not Found!!");
        }
        return new ResponseEntity<>(new ApiResponceDelete<>(habit), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiRespones<Habit>> updateHabit(@PathVariable UUID id, @Valid @RequestBody HabitRequest habitRequest) {
        ApiRespones<Habit> response = ApiRespones.<Habit>builder()
                .success(true)
                .message("Habit successfully Updated")
                .status(HttpStatus.OK)
                .payload(habitService.updateHabit(id, habitRequest))
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespones<Habit>> getHabitById(@PathVariable("id") UUID habitId)  {
        ApiRespones<Habit> response = ApiRespones.<Habit>builder()
                .success(true)
                .message("Habit fetched successfully!!!")
                .status(HttpStatus.OK)
                .payload(habitService.getHabitById(habitId))
                .timestamps(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @GetMapping("/currentUser")
//    public ResponseEntity<ApiRespones<Habit>> getCurrentUserHabit() {
//        ApiRespones<Habit> response = ApiRespones.<Habit>builder()
//                .success(true)
//                .message("Get Current User Habit!!!")
//                .status(HttpStatus.OK)
//                .payload(null)
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

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


}
