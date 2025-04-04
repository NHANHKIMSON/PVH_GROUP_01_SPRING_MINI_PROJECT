package org.example.pvh_group_01_spring_mini_project.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    private UUID achievementId = UUID.randomUUID();
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}
