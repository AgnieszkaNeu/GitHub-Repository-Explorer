package com.example.demo;

import com.example.demo.Entity.Branch;
import com.example.demo.Entity.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExtractData {

    public GithubApiService gitService;

    public ExtractData(GithubApiService gitService) {
        this.gitService = gitService;
    }

    public List<Repository> extractRepositories(String username){
        ResponseEntity<List> response = gitService.getUserRepositories(username);

        List<Map<String, Object>> repos = response.getBody();

        return repos
                .stream()
                .filter(repo -> !(Boolean) repo.get("fork"))
                .map(repo -> {
                    Map<String, Object> owner = (Map<String, Object>) repo.get("owner");
                    String login = (String) owner.get("login");

                    return new Repository(
                            login,
                            (String) repo.get("name"));
                })
                .collect(Collectors.toList());

    }

    public List<Branch> extractBranchFromResponse(String username, String repository){
        ResponseEntity<List> response = gitService.getBranch(username,repository);
        List<Map<String, Object>> branches = response.getBody();

        return branches
                .stream()
                .map(branch -> {
                    Map<String,Object> commit = (Map<String, Object>) branch.get("commit");
                    String sha = (String) commit.get("sha");

                    return new Branch(
                            (String) branch.get("name"),
                            sha
                    );
                }).collect(Collectors.toList());
    }
}
