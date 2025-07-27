package com.example.demo.Entity;

public record GithubApiBranch(
    String name,
    GithubApiCommit commit
) { }
