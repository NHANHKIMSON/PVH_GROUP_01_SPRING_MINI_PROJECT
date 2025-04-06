package org.example.pvh_group_01_spring_mini_project.service.impl;

import org.example.pvh_group_01_spring_mini_project.exception.NotFoundException;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.repository.HabitRepository;
import org.example.pvh_group_01_spring_mini_project.service.HabitLogService;
import org.example.pvh_group_01_spring_mini_project.service.HabitService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return habitRepository.getHabitById(habitId);
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        Habit habit = habitRepository.createHabit(habitRequest);
        habit.setIsActive(true);
        habit.setCreateAt(LocalDateTime.now());

        return habit;
    }

    @Override
    public Habit deleteHabit(UUID id) {
        Habit habit = habitRepository.deleteHabit(id);
        if (habit == null) {
            throw new NotFoundException("Habit Id " + id + "Not Found!!");
        }
        System.out.println(habit);
        return habit;
    }

    //    update this method to get ENUM field for Frequency when user Request Update Habit
    @Override
    public Habit updateHabit(UUID id, HabitRequest habitRequest) {
        Habit habit = habitRepository.getHabitById(id);
        if (habit == null){
            throw new IllegalArgumentException("Habit not found" + id);
        }

        habit.setTitle(habitRequest.getTitle());
        habit.setDescription(habitRequest.getDescription());
        if (habitRequest.getFrequency() != null){
            habit.setFrequency(habitRequest.getFrequency().toString());
        }
        return habitRepository.updateHabit(id, habitRequest);
    }






}