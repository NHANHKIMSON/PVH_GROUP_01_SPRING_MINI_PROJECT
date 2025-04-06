package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

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
}
