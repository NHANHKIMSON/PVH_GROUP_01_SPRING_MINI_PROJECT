package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.repository.UserAppRepository;
import org.example.pvh_group_01_spring_mini_project.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserAppRepository userAppRepository;
    @Override
    public UserApp getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        return userAppRepository.findByEmail(email);
    }
}
