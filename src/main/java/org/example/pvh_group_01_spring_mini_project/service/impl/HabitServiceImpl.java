package org.example.pvh_group_01_spring_mini_project.service.impl;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.repository.HabitRepository;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.example.pvh_group_01_spring_mini_project.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public List<Habit> getAllHabits(Integer page, Integer size) {
        if (page == null || page <= 0) {
            throw new IllegalArgumentException("page must be greater than 0");
        }
        return habitRepository.getAllHabits(page, size);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        return null;
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        return null;
    }


}
