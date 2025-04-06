package org.example.pvh_group_01_spring_mini_project.controllers;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.service.AuthService;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final UserAppService userAppService;

    @PostMapping
    public UserApp register(@RequestBody AuthRegisterRequest authRegisterRequest) {
        return userAppService.registerProfile(authRegisterRequest);
    }


}
