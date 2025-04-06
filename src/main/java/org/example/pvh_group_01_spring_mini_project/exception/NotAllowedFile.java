package org.example.pvh_group_01_spring_mini_project.exception;

public class NotAllowedFile extends RuntimeException {
    public NotAllowedFile(String message) {
        super(message);
    }
}
