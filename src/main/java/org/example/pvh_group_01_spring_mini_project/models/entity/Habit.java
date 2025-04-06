package org.example.pvh_group_01_spring_mini_project.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habit extends UUIDTypeHandler {
    private UUID habitId;
    private String title;
    private String description;
    private Frequency frequency;
    private Boolean isActive;
    private Profile appUserResponse;
//    private UUID appUserId; // updated
    private LocalDateTime createAt;
}