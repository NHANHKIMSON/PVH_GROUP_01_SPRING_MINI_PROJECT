package org.example.pvh_group_01_spring_mini_project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.service.ProfileService;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserAppService userAppService;

    @DeleteMapping
    @Operation(summary = "Delete Profile")
    public ResponseEntity<ApiRespones> deleteProfile(@RequestParam String username) {
        ApiRespones response = ApiRespones.builder()
                .success(true)
                .message("Deleted profile with username: " + username)
                .payload(null)
                .status(HttpStatus.OK)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }



    @GetMapping
    @Operation(summary = "Get current authenticated user's profile")
    public ResponseEntity<ApiRespones<UserAppService>> getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApiRespones response = ApiRespones.builder()
                .success(true)
                .message("Fetched profile of current user")
                .payload(userAppService.loadUserByUsername(authentication.getName()))
                .status(HttpStatus.OK)
                .timestamps(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}