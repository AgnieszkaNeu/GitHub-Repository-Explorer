package com.example.demo.Entity;

public record GithubApiBranch(
    String name,
    String sha,
    GithubApiCommit commit
) { }
