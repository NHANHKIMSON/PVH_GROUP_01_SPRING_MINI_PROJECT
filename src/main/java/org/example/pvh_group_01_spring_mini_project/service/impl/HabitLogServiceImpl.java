package org.example.pvh_group_01_spring_mini_project.service.impl;

import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.repository.HabitLogRepository;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitLogServiceImpl implements HabitLogService {
    private final HabitLogRepository habitLogRepository;

    public HabitLogServiceImpl(HabitLogRepository habitLogRepository) {
        this.habitLogRepository = habitLogRepository;
    }

    @Override
    public HabitLog addHabitLog(HabitLogRequest habitLogRequest) {
        HabitLog habitLog = new HabitLog();

        if (habitLogRequest.getStatus() == Frequency.COMPLETED) {
            int completedCount = habitLogRepository.countCompletedLogs(habitLogRequest.getHabitId());
            habitLog.setXpEarned((completedCount + 1) * 10);
        } else {
            habitLog.setXpEarned(0);
        }
        return habitLogRepository.addHabitLog(habitLogRequest);
    }

    @Override
    public List<HabitLog> getAllHabitLog() {
        return habitLogRepository.getAllHabitLog();
    }
}
