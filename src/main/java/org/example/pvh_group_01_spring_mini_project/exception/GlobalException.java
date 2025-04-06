package org.example.pvh_group_01_spring_mini_project.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ErrorRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import io.minio.errors.ErrorResponseException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Something went wrong!!!");
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("Timestamp", LocalDateTime.now());
        return problemDetail;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptionErrors(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Something went wrong!!!");
        problemDetail.setTitle("Invalid Input");



//   add new :  Handle when page=0
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation error");
//        problemDetail.setTitle("Bad Request");
//
//        problemDetail.setProperty("errors", Map.of("page", e.getMessage()));
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//
//        return problemDetail;
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
//        // Create ProblemDetail response
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation error");
//        problemDetail.setTitle("Bad Request");
//
//        // Extract validation errors (without using Stream)
//        List<String> errors = new ArrayList<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//
//        // Attach validation errors as additional details
//        problemDetail.setProperty("errors", errors);
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//
//        return problemDetail;
//    }

        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        problemDetail.setProperty("Errors", errors);
        problemDetail.setProperty("Timestamp", LocalDateTime.now());
        return problemDetail;
    }


    //handle connect to minio exception
    @ExceptionHandler(ConnectException.class)
    public  ProblemDetail HandleConnectionMinioException(ConnectException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Connection failed");
        problemDetail.setDetail("Connection is failed ");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
}
