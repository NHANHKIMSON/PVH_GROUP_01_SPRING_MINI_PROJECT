package org.example.pvh_group_01_spring_mini_project.service;

import org.example.pvh_group_01_spring_mini_project.models.dto.request.ProfileRequest;
import org.example.pvh_group_01_spring_mini_project.models.entity.Profile;

public interface FileService {


    Profile registerProfile(ProfileRequest profile);

}
