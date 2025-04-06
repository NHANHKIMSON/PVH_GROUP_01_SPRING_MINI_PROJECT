package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.UUID;

@Mapper
public interface ProfileRepository {

    // Write from leap for test get habitLog
    @Select("""
        SELECT * FROM app_users WHERE app_user_id = #{appUserId}
    """)
    @Results(id = "profileMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    Profile getHabitProfile(@Param("appUserId") UUID appUserId);

}
