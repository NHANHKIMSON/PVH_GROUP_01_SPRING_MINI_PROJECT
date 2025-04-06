package org.example.pvh_group_01_spring_mini_project.exception;

import org.example.pvh_group_01_spring_mini_project.models.dto.response.ErrorRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import io.minio.errors.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFound(NotFoundException ex){
        return new ResponseEntity<>(new ErrorRespone(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlankInputException.class)
    public ResponseEntity<?> handlerBlankInput(BlankInputException ex){
        return new ResponseEntity<>(new ErrorRespone(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    // handle file exception
    @ExceptionHandler(NotAllowedFile.class)
    public ProblemDetail handleFileNotAllow(NotAllowedFile e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail("Profile image must be a valid image URL ending with .png, .svg, .jpg, .jpeg, or .gif");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ProblemDetail handleErrorRespone(ErrorResponseException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Not found");
        problemDetail.setDetail("This profile image is not found!");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;

    }
    // end handle file exception


}
