package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.UUID;
import org.example.pvh_group_01_spring_mini_project.config.FrequencyTypeHandler;

import java.util.List;

@Mapper
public interface HabitRepository {

    @Select("""
            DELETE FROM habits WHERE habit_id = #{id} returning *
            """)
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "profileId", column = "app_user_id", typeHandler = UUIDTypeHandler.class, one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getProfileById")),
            @Result(property = "createAt", column = "created_at")
    })
    Habit deleteHabit(UUID id);

    @Select("""
        UPDATE habits
        SET title = #{request.title}, description = #{request.description}, frequency = #{request.frequency}
        WHERE habit_id = #{id}
        returning *
    """)
    @ResultMap("habitMapper")
    Habit updateHabit(UUID id, @Param("request") HabitRequest habitRequest);

    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    @ResultMap("habitMapper")
    Habit getHabitById(UUID habitId);

//    Habit getCurrentUserHabit();

    @Select("""
        SELECT * FROM habits
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
//    @Results(id="habitMap", value = {
//            @Result(property = "habitId", column = "habit_id"),
//            @Result(property = "isActive", column = "is_active"),
//            @Result(property = "createAt", column = "created_at"),
//            @Result(property = "frequency", column = "frequency", typeHandler = FrequencyTypeHandler.class),
//            @Result(property = "profileId", column = "app_user_id",
//                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.ProfileRepository.getProfileById")
//            )
//    })
    @ResultMap("habitMapper")
    List<Habit> getAllHabits(Integer page, Integer size);

    @Select("""
         INSERT INTO habits(habit_id, title, description, frequency,is_active, app_user_id , created_at)
        VALUES (gen_random_uuid(),#{request.title}, #{request.description}, #{request.frequency},true, '2163d25a-1792-4c65-b626-ab0085165b98', now())
        RETURNING *
 
    """)
    @ResultMap("habitMapper")
    Habit createHabit(@Param("request") HabitRequest habitRequest);


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
