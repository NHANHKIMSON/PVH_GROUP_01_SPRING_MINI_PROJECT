package org.example.pvh_group_01_spring_mini_project.models.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLog extends UUIDTypeHandler {

    private UUID habitLogId;
    private LocalDate logDate;

    @NotBlank(message = "Status can be empty")
    private Frequency status;
    private Integer xpEarned;
    private Habit habit;
    private LocalDateTime createdAt;
}