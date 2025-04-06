package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;

import java.util.List;
import java.util.UUID;

public interface HabitLogService {
    HabitLog addHabitLog(HabitLogRequest habitLogRequest);
    List<HabitLog> getAllHabitLog(Integer offset, Integer size, UUID habitId);
}