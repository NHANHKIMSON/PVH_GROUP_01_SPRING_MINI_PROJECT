package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.UUID;

import org.example.pvh_group_01_spring_mini_project.config.FrequencyTypeHandler;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;

import java.util.List;

@Mapper
public interface HabitRepository {

    // Write from leap for test get habitLog
    @Select("""
        SELECT * FROM habits WHERE habit_id = #{habitId}
       
    """)
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUserResponse", column = "app_user_id", one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getHabitProfile")),
            @Result(property = "createAt", column = "created_at")
    })
    Habit getHabitById(@Param("habitId") UUID habitId);

    @Select("""
        UPDATE app_users
        SET xp = xp + (
            SELECT xp_earned
            FROM habit_logs
            WHERE habit_id = #{habitId}
            ORDER BY created_at DESC
            LIMIT 1
        )
        WHERE app_user_id = (
            SELECT app_user_id FROM habits WHERE habit_id = #{habitId}
        )
    """)
    Habit updateUserXpByHabitId(UUID habitId);


    @Select("""
        SELECT * FROM habits
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
//    @Results(id="habitMapper", value = {
//            @Result(property = "habitId", column = "habit_id"),
//            @Result(property = "isActive", column = "is_active"),
//            @Result(property = "createAt", column = "created_at"),
//            @Result(property = "frequency", column = "frequency", typeHandler = FrequencyTypeHandler.class),
//            @Result(property = "appUserResponse", column = "app_user_id",
//                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getProfileById")
//            )
//    })
    @ResultMap("habitMapper")
    List<Habit> getAllHabits(Integer page, Integer size);

//    @Select("""
//        SELECT * FROM habits
//        WHERE habit_id = #{habitId}
//    """)
//    @ResultMap("habitMapper")
//    Habit getHabitById(UUID habitId);

    @Select("""
        INSERT INTO habits(title, description, frequency)
        VALUES (#{request.title}, #{request.description}, #{request.frequency})
        RETURNING *

    """)
    @ResultMap("habitMapper")
    Habit createHabit(@Param("request") HabitRequest habitRequest);
}
