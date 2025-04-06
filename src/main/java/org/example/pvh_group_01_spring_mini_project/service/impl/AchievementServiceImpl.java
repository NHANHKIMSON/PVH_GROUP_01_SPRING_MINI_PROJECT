package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.entity.Achievement;
import org.example.pvh_group_01_spring_mini_project.repository.AchievementRepository;
import org.example.pvh_group_01_spring_mini_project.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAch(Integer page, Integer size) {
        return achievementRepository.getAllAch(page, size);
    }

    @Override
    public List<Achievement> getAchByid(Integer page, Integer size) {
        return achievementRepository.getAchByid(page, size);
    }
}
