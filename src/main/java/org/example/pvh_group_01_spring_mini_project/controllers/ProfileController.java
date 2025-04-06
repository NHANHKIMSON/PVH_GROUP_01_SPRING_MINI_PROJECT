package org.example.pvh_group_01_spring_mini_project.controllers;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/id")
    public ResponseEntity<ApiRespones<Profile>> getProfileById(@RequestParam UUID id) {
        ApiRespones<Profile> apiRespones = ApiRespones.<Profile>builder()
                .success(true)
                .message("Get Profile By ID")
                .status(HttpStatus.OK)
                .payload(profileService.getProfileById(id))
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiRespones);
    }
}

