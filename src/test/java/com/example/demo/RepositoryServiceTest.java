package com.example.demo;

import com.example.demo.Entity.GithubApiBranch;
import com.example.demo.Entity.GithubApiCommit;
import com.example.demo.Entity.GithubApiRepository;
import com.example.demo.Services.GithubApiService;
import com.example.demo.Services.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepositoryServiceTest {

    @Mock
    private GithubApiService githubApiService;

    @InjectMocks
    private RepositoryService repositoryService;

    @Test
    void shouldReturnRepositoryWithBranches() {

        var commit = new GithubApiCommit("sha");
        var githubApiRepository = new GithubApiRepository("Repository branchName", false);
        var githubApiBranch = new GithubApiBranch("Branch name", commit) ;

        when(githubApiService.getUserRepositories("username"))
                .thenReturn(List.of(githubApiRepository));
        when(githubApiService.getBranch("username", "Repository branchName"))
                .thenReturn(List.of(githubApiBranch));

        var result = repositoryService.getRepositoryWithBranches("username");

        assertEquals(1, result.size());
        assertEquals("username", result.get(0).login());
        assertEquals("Repository branchName", result.get(0).repositoryName());
        assertEquals("Branch name",result.get(0).branches().get(0).branchName());
        assertEquals("sha",result.get(0).branches().get(0).sha());
    }

    @Test
    void shouldFilterOutForks(){

        var githubApiRepository = new GithubApiRepository("Repository branchName", true);
        when(githubApiService.getUserRepositories("username"))
                .thenReturn(List.of(githubApiRepository));

        var result = repositoryService.getRepositoryWithBranches("username");
        assertEquals(0,result.size());
    }
}
