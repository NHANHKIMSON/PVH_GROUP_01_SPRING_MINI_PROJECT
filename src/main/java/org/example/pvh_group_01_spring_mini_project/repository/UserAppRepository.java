package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.*;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.util.List;

@Mapper
public interface UserAppRepository {
    @Select("""
 
            INSERT INTO app_users( app_user_id,username, email, password,profile_image)
 VALUES (gen_random_uuid(),#{request.username}, #{request.email}, #{request.password}, #{request.profileImageUrl} )
 RETURNING *;
 
    """)

    @Results(id = "UserAppMapper", value = {
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    UserApp registerProfile(@Param("request") AuthRegisterRequest request);


    @Select(
            """
        SELECT * FROM app_users where email=#{email}
"""
    )
    @ResultMap("UserAppMapper")
    UserApp getProfileByEmail(String email);

}
