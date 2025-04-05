package org.example.pvh_group_01_spring_mini_project.exception;

import org.example.pvh_group_01_spring_mini_project.models.dto.response.ErrorRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFound(NotFoundException ex){
        return new ResponseEntity<>(new ErrorRespone(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlankInputException.class)
    public ResponseEntity<?> handlerBlankInput(BlankInputException ex){
        return new ResponseEntity<>(new ErrorRespone(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
