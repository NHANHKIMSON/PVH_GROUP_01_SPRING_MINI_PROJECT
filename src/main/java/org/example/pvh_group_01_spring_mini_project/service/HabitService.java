package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;

import java.util.UUID;

public interface HabitService {
    Habit deleteHabit(UUID id);
    Habit updateHabit(UUID id, HabitRequest habitRequest);
    Habit getHabitById(UUID habitId);
    Habit getCurrentUserHabit();
}


