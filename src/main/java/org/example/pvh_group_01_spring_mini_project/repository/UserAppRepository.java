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

    @Select("""
            SELECT * FROM app_users WHERE email = #{email} LIMIT 1
            """)
    @ResultMap("UserAppMapper")
    UserApp findByEmail(String email);

    @Insert("""
            INSERT INTO app_users (app_user_id,username,email, password,otp,is_verified,created_at) VALUES (gen_random_uuid(),#{user.username},#{user.email}, #{user.password},#{user.otp},false,#{user.createdAt}) LIMIT 1
            """)
    @ResultMap("UserAppMapper")
    void save(@Param("user") UserApp user);

    //
    @Update("""
            UPDATE app_users SET is_verified  = #{user.isVerified} WHERE otp = #{user.otp}
            """)
    @ResultMap("UserAppMapper")
    void update(@Param("user") UserApp user);

    @Update("""
            UPDATE app_users SET otp = #{user.otp}, created_at = #{user.createdAt} WHERE email = #{user.email}
            """)
    @ResultMap("UserAppMapper")
    void updateOtp(@Param("user") UserApp user);


}
