package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    //After clone started form here
    @Select("""
            SELECT * FROM habit_logs WHERE habit_id = #{habitId}
            OFFSET #{offset} LIMIT #{size}
            """)
    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit", column = "habit_id",
                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.HabitRepository.getHabitById"), typeHandler = UUIDTypeHandler.class),
            @Result(property = "createdAt", column = "created_at")
    })
    List<HabitLog> getAllHabitLog(@Param("offset") Integer offset,
                                  @Param("size") Integer size,
                                  @Param("habitId") UUID habitId);


    @Select("""
    INSERT INTO habit_logs (habit_log_id,status, habit_id, log_date, created_at, xp_earned)
    VALUES (
         gen_random_uuid(),
        #{request.status},  
        #{request.habitId}, 
        CURRENT_TIMESTAMP,  
        CURRENT_TIMESTAMP, 
        CASE
            WHEN #{request.status} = 'COMPLETED' THEN 10  ELSE 0 
        END
    )
    RETURNING habit_log_id, log_date, status, xp_earned, habit_id, created_at
    """)
    @ResultMap("habitLogMapper")
    HabitLog addHabitLog(@Param("request") HabitLogRequest habitLogRequest);


}
