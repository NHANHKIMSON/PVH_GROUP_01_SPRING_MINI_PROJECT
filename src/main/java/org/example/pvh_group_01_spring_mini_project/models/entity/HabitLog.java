package org.example.pvh_group_01_spring_mini_project.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLog {
    private UUID habitLogId = UUID.randomUUID();
    private LocalDate logDate;
    private Frequency status;
    private Integer xpEarned;
    private Habit habit;
    private LocalDateTime createdAt;
}