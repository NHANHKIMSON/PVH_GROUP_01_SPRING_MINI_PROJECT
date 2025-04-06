package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.config.FrequencyTypeHandler;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Select("""
        SELECT * FROM habits
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
    @Results(id="habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createAt", column = "created_at"),
            @Result(property = "frequency", column = "frequency", typeHandler = FrequencyTypeHandler.class),
            @Result(property = "appUserResponse", column = "app_user_id",
                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getProfileById")
            )
    })
    List<Habit> getAllHabits(Integer page, Integer size);

    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    @ResultMap("habitMapper")
    Habit getHabitById(UUID habitId);

//    @Select("""
//        INSERT INTO habits(title, description, frequency)
//        VALUES (#{request.title}, #{request.description}, #{request.frequency})
//        RETURNING *
//
//    """)
//    @ResultMap("habitMapper")
//    Habit createHabit(@Param("request") HabitRequest habitRequest);
}
