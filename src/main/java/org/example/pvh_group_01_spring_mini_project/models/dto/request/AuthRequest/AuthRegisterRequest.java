package org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequest {
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
}
