package org.example.pvh_group_01_spring_mini_project.models.dto.request.profileRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String identifier;
    private String profileImageUrl;
}
