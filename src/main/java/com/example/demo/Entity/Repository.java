package com.example.demo.Entity;

import java.util.List;

public record Repository(
        String login,
        String name,
        List<Branch> branches
){ }
