package com.example.demo.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username){
        super(username);
    }

}
