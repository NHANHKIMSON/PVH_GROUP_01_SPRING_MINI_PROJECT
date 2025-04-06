package org.example.pvh_group_01_spring_mini_project.service;

import org.apache.ibatis.annotations.Param;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;
import java.util.UUID;

public interface HabitService {
//    List<Habit> getAllHabits(Integer page, Integer size);
    Habit getHabitById(UUID habitId);
//    Habit deleteHabit(UUID id);
//    Habit updateHabit(UUID id, HabitRequest habitRequest);
//    Habit addHabit(HabitRequest habitRequest);
}