package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;

import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAppService extends UserDetailsService {
    void verify(String email, String otp);

    UserApp registerProfile(AuthRegisterRequest authRegisterRequest);

    UserApp checkEmail(String email);
}
