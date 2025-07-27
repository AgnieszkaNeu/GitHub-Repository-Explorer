package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(GitController.class)
public class GitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    MockMvcTester tester;

    @Test
    void shouldReturnRepositories(){

    }
}
