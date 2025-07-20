package com.example.demo.Exceptions;

import com.example.demo.Entity.GithubApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GithubApiResponse> handleUserNotFoundException(UserNotFoundException exception){
        GithubApiResponse response = new GithubApiResponse(404, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
