package org.example.pvh_group_01_spring_mini_project.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {
    private String title;
    private String description;
    private Frequency frequency;
}