package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.HabitLogRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.HabitLog;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;

@Mapper
public interface HabitLogRepository {

    //After clone started form here
    @Select("""
            SELECT * FROM habit_logs
            """)
    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit", column = "habit_id",
                    one = @One(select = "org.example.pvh_group_01_spring_mini_project.repository.HabitRepository.getHabitById")),
            @Result(property = "createdAt", column = "created_at")
    })
    List<HabitLog> getAllHabitLog();


    @Select("""
    INSERT INTO habit_logs (status, habit_id, log_date, created_at, xp_earned)
    VALUES (
               #{request.status},  
               #{request.habitId}, 
               CURRENT_TIMESTAMP,  
               CURRENT_TIMESTAMP, 
               CASE
                   WHEN #{request.status} = 'COMPLETED' THEN 10  
                   ELSE 0 
                   END
           )
    ON CONFLICT (habit_id) 
        DO UPDATE
        SET xp_earned = habit_logs.xp_earned + CASE
                                                   WHEN EXCLUDED.status = 'COMPLETED' THEN 10 
                                                   ELSE 0 
                                                 END
    RETURNING habit_log_id, log_date, status, xp_earned, habit_id, created_at
    """)
    @ResultMap("habitLogMapper")
    @Options(useGeneratedKeys = true, keyProperty = "habitLogId")
    HabitLog addHabitLog(@Param("request") HabitLogRequest habitLogRequest);


}
