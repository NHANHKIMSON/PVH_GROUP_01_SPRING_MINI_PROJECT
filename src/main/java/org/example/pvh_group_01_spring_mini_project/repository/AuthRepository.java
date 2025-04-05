package org.example.pvh_group_01_spring_mini_project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.ProfileRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;

@Mapper
public interface AuthRepository {


    @Select("""
insert into 

""")

    Profile registerProfile(@Param("request") ProfileRequest profile);



}
