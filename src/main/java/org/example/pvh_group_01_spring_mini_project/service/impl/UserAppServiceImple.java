package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.repository.UserAppRepository;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppServiceImple implements UserAppService {
    private final UserAppRepository userAppRepository;
    @Override
    public UserApp registerProfile(AuthRegisterRequest authRegisterRequest) {
        return userAppRepository.registerProfile(authRegisterRequest);
    }
}
