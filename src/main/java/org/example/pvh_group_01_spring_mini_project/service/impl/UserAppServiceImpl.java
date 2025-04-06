package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.repository.UserAppRepository;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserApp registerProfile(AuthRegisterRequest authRegisterRequest) {
        authRegisterRequest.setPassword(passwordEncoder.encode(authRegisterRequest.getPassword()));
        return  userAppRepository.registerProfile(authRegisterRequest);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userAppRepository.getProfileByEmail(email);
    }
}
