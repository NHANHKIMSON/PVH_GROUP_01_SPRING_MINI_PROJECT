package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.UUID;


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

    @ResultMap("habitMapper")
    Habit updateUserXpByHabitId(@Param("habitId") UUID habitId);

    @Select("""
    UPDATE app_users
    SET level = FLOOR(xp / 100)
    WHERE app_user_id = (
        SELECT app_user_id FROM habits WHERE habit_id = #{habitId}
    )
    AND MOD(xp, 100) = 0
    """)
    @ResultMap("habitMapper")
    Habit updateUserLevelByHabitId(@Param("habitId") UUID habitId);





}
