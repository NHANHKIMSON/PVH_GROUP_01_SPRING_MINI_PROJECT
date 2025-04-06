package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.entity.Achievement;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAllAch(Integer page, Integer size);
    List<Achievement> getAchByid(Integer page, Integer size);
}
