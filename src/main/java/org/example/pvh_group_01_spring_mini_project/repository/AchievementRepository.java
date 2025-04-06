package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AchievementRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Achievement;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;

@Mapper
public interface AchievementRepository {
    @Select("""
        SELECT * FROM achievement LIMIT #{size} OFFSET (#{page}-1) * #{size};
    """)
    @Results(id = "AchievementMapper", value = {
            @Result(property = "achievementId", column = "achievement_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "xpRequired", column = "xp_required")
    })
    List<Achievement> getAllAch(Integer page, Integer size);

    @Select("""
        SELECT a.achievement_id,a.title,a.description,a.badge,a.xp_required
        FROM app_user_achievements aua FULL OUTER JOIN achievement a on aua.achievement_id = a.achievement_id
        LIMIT #{size} OFFSET (#{page}-1) * #{size};
    """)
    @ResultMap("AchievementMapper")
    List<Achievement> getAchByid(Integer page, Integer size);
}
