package com.example.demo;

import com.example.demo.Entity.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitController {

    public ExtractData extractData;
    public GithubApiService githubApiService;

    public GitController(ExtractData extractData, GithubApiService githubApiService) {
        this.extractData = extractData;
        this.githubApiService = githubApiService;
    }

    @GetMapping()
    public List<Repository> getRepositoriesForUser (@RequestParam String username){

        githubApiService.getUser(username);

        List<Repository> data = extractData.extractRepositories(username);

        data.forEach(
                repository -> repository.setBranches(extractData.extractBranchFromResponse(username, repository.getName()))
        );

        return data;
    }

}
