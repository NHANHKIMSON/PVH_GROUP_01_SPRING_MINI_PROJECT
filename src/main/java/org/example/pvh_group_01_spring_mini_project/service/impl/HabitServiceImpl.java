package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.repository.HabitRepository;
import org.example.pvh_group_01_spring_mini_project.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    @Override
    public Habit deleteHabit(UUID id) {
        Habit habit = habitRepository.deleteHabit(id);
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
            habit.setFrequency(habitRequest.getFrequency());
        }
        return habitRepository.updateHabit(id, habitRequest);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        return habitRepository.getHabitById(habitId);
    }

//    @Override
//    public Habit getCurrentUserHabit() {
//
//        return null;
//    }

    @Override
    public List<Habit> getAllHabits(Integer page, Integer size) {
        if (page == null || page <= 0) {
            throw new IllegalArgumentException("page must be greater than 0");
        }
        return habitRepository.getAllHabits(page, size);
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest) {
        return habitRepository.createHabit(habitRequest);
    }

//    public Profile getCurrentUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//    }


}
