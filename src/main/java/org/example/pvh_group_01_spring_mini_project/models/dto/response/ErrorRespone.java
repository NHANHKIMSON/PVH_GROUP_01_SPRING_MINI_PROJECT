package org.example.pvh_group_01_spring_mini_project.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorRespone<T> {
    private String message;
    private HttpStatus status;
//    private T payload;
    private LocalDateTime timespam;
}
