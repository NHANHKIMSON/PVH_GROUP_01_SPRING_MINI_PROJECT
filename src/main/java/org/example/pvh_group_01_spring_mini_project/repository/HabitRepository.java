package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.config.FrequencyTypeHandler;
import org.example.pvh_group_01_spring_mini_project.config.UuidTypeHandler;
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
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createAt", column = "created_at"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "appUserResponse", column = "app_user_id",
                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getProfileById"))
    })
    List<Habit> getAllHabits(Integer page, Integer size);

    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    @ResultMap("habitMapper")
    Habit getHabitById(@Param("habitId") UUID habitId);

    @Select("""
         INSERT INTO habits(title, description, frequency, is_active, app_user_id, created_at)
         VALUES (#{request.title}, #{request.description}, #{request.frequency}, true, '20000000-0000-4000-a000-000000000001', now())
         RETURNING *
    """)
    @ResultMap("habitMapper")
    Habit createHabit(@Param("request") HabitRequest habitRequest);

    @Select("""
        DELETE FROM habits
        WHERE habit_id = #{id}
        RETURNING *
    """)
    @ResultMap("habitMapper")
    Habit deleteHabit(UUID id);

    @Select("""
        UPDATE habits
        SET title = #{request.title}, description = #{request.description}, frequency = #{request.frequency}
        WHERE habit_id = #{id}
        RETURNING *
    """)
    @ResultMap("habitMapper")
    Habit updateHabit(UUID id, @Param("request") HabitRequest habitRequest);

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
    Habit updateUserXpByHabitId(@Param("habitId") UUID habitId);

    @Select("""
        UPDATE app_users
        SET level = FLOOR(xp / 100)
        WHERE app_user_id = (
            SELECT app_user_id FROM habits WHERE habit_id = #{habitId}
        )
        AND MOD(xp, 100) = 0
    """)
    Habit updateUserLevelByHabitId(@Param("habitId") UUID habitId);
}
