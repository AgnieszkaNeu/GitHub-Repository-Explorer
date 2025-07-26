package com.example.demo.Exceptions;

public class GithubApiException extends RuntimeException{

    public GithubApiException(String message){
        super(message);
    }
}
