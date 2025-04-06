package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;

import java.util.List;
import java.util.UUID;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;

import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size);
    Habit getHabitById(UUID habitId);
    Habit createHabit(HabitRequest habitRequest);
    Habit deleteHabit(UUID id);
    Habit updateHabit(UUID id, HabitRequest habitRequest);
}
