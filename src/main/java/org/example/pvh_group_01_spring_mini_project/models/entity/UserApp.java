package org.example.pvh_group_01_spring_mini_project.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApp extends UUIDTypeHandler {
    private UUID appUserId;
    private String username;
    private String email;
    private String password;
    private Integer level;
    private Integer xp;
    private String profileImageUrl;
    private Boolean isVerified;
    private LocalDateTime createdAt;
}