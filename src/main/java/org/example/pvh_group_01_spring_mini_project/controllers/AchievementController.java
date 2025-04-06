package org.example.pvh_group_01_spring_mini_project.controllers;

import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Achievement;
import org.example.pvh_group_01_spring_mini_project.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public ResponseEntity<ApiRespones<List<Achievement>>> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        ApiRespones<List<Achievement>> respones = ApiRespones.<List<Achievement>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Successfully")
                .payload(achievementService.getAllAch(page, size))
                .timestamps(LocalDateTime.now()).build();
        return ResponseEntity.ok(respones);
    }

    @GetMapping("/app-users")
    public ResponseEntity<ApiRespones<List<Achievement>>> getAllById(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        ApiRespones<List<Achievement>> respones1 = ApiRespones.<List<Achievement>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Successfully")
                .payload(achievementService.getAchByid(page, size))
                .timestamps(LocalDateTime.now()).build();
        return ResponseEntity.ok(respones1);
    }
}
