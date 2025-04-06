package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;

public interface UserAppService {
    UserApp registerProfile(AuthRegisterRequest authRegisterRequest);
}
