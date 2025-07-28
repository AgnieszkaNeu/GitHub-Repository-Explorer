package com.example.demo;

import com.example.demo.Entity.Branch;
import com.example.demo.Entity.Repository;
import com.example.demo.Services.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitController.class)
public class GitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepositoryService repositoryService;


    @Test
    void shouldReturnRepositories() throws Exception {
        Branch branch = new Branch("Branch name", "sha");
        Repository repository = new Repository("username", "Repository name", List.of(branch));

        when(repositoryService.getRepositoryWithBranches("username")).thenReturn(List.of(repository));

        mockMvc.perform(get("/?username=username").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].login").value("username"));
    }
}
