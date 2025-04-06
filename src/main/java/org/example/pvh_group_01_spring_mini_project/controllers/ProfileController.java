package org.example.pvh_group_01_spring_mini_project.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.service.ProfileService;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @PostMapping
    @Operation(summary = "Get current authenticated user's profile")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiRespones> updateProfile() {
//        UserApp user = userAppService.getCurrentUser();

        ApiRespones response = ApiRespones.builder()
                .success(true)
                .message("Fetched profile of current user")
//                .payload(user)
                .status(HttpStatus.OK)
                .timestamps(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Get current authenticated user's profile")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiRespones> deleteProfile() {
//        UserApp user = userAppService.getCurrentUser();

        ApiRespones response = ApiRespones.builder()
                .success(true)
                .message("Fetched profile of current user")
//                .payload(user)
                .status(HttpStatus.OK)
                .timestamps(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}

