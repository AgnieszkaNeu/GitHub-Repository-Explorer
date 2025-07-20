package com.example.demo.Entity;

public class GithubApiResponse {

    public int status;
    public String message;

    public GithubApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
