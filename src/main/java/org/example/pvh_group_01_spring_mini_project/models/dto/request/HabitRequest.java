package org.example.pvh_group_01_spring_mini_project.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {
    @NotBlank(message = "title must not be blank")
    private String title;
    @NotBlank(message = "description must not be blank")
    private String description;
    @NotNull
    private Frequency frequency;
}