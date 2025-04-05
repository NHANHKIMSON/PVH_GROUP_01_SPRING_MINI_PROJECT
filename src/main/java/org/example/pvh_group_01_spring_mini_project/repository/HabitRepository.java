package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Select("""
        SELECT * FROM habits
        OFFSET (#{page}-1) * #{size}
        LIMIT #{size}
    """)
    @Results(id="habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createAt", column = "created_at"),
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class)
    })
    List<Habit> getAllHabits(Integer page, Integer size);

    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    @ResultMap("habitMapper")
    Habit getHabitById(UUID habitId);

    @Select("""
            DELETE FROM habits WHERE habit_id = #{id} returning *;
            """)
    @ResultMap("habitMapper")
    Habit deleteHabit(UUID id);

    @Select("""
        UPDATE habits
        SET title = #{request.title}, description = #{request.description}, frequency = #{request.frequency}
        WHERE habit_id = #{id}
        returning *
    """)
    @ResultMap("habitMapper")
    Habit updateHabit(UUID id, @Param("request") HabitRequest habitRequest);
}