package org.example.pvh_group_01_spring_mini_project.service.impl;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.repository.HabitLogRepository;
import org.example.pvh_group_01_spring_mini_project.repository.HabitRepository;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitLogServiceImpl implements HabitLogService {
    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    public HabitLogServiceImpl(HabitLogRepository habitLogRepository, HabitRepository habitRepository) {
        this.habitLogRepository = habitLogRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public HabitLog addHabitLog(HabitLogRequest habitLogRequest) {
        HabitLog habitLog = habitLogRepository.addHabitLog(habitLogRequest);
        habitRepository.updateUserXpByHabitId(habitLog.getHabit().getHabitId());
        return habitLog;
    }


    @Override
    public List<HabitLog> getAllHabitLog(Integer offset, Integer size, UUID habitId) {
        return habitLogRepository.getAllHabitLog(offset, size, habitId);
    }

}
