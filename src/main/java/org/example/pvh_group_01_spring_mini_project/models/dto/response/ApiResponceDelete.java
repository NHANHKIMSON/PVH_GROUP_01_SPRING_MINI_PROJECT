package org.example.pvh_group_01_spring_mini_project.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.entity.Habit;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiResponceDelete<T> {
    private Boolean success;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamps;

    public ApiResponceDelete(Habit habit) {
        this.success = true;
        this.message = "Success";
        this.status = HttpStatus.OK;
        this.timestamps = LocalDateTime.now();
    }
}

