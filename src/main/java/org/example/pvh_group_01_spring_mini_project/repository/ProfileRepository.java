package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.UUID;

@Mapper
public interface ProfileRepository {

    @Select("""
        SELECT * FROM app_users WHERE app_user_id = #{id}
    """)
    @Results(id = "habitMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    Profile getProfileById(UUID id);


}
