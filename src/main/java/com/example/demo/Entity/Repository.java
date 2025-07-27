package com.example.demo.Entity;

import java.util.List;

public record Repository(
        String login,
        String repositoryName,
        List<Branch> branches
){ }
