package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;

import java.util.UUID;

public interface ProfileService {
    Profile getProfileById(UUID id);
}
