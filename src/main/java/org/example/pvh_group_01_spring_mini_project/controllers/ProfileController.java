package org.example.pvh_group_01_spring_mini_project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.repository.UserAppRepository;
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
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final ProfileService profileService;
    private final UserAppService userAppService;
    private final UserAppRepository userAppRepository;

    @GetMapping
    @Operation(summary = "Get current authenticated user's profile")
    public ResponseEntity<ApiRespones> getCurrentUser() {
        System.out.println("Hello");
        UserApp userApp = userAppService.getCurrentUser();
        ApiRespones response = ApiRespones.builder()
                .success(true)
                .message("Fetched current user successfully")
                .payload(userApp)
                .status(HttpStatus.OK)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}