package org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {
    private String identifier;
    private String password;
}
