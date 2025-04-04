package org.example.pvh_group_01_spring_mini_project.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementRequest {
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}
