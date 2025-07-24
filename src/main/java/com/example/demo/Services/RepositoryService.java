package com.example.demo.Services;

import com.example.demo.Entity.Branch;
import com.example.demo.Entity.GithubApiBranch;
import com.example.demo.Entity.GithubApiRepository;
import com.example.demo.Entity.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {

    public GithubApiService githubApiService;

    public RepositoryService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public List<Repository> getRepositoryWithBranches(String username){
        githubApiService.checkIfUserExists(username);

        List<GithubApiRepository> GithubApiRepositories = githubApiService.getUserRepositories(username)
                                                        .stream()
                                                        .filter(repo -> !repo.fork())
                                                        .toList();

        return GithubApiRepositories.stream()
                .map(repo -> {
                    List<GithubApiBranch> githubApiBranches = githubApiService.getBranch(username,repo.name());
                    List<Branch> branches = githubApiBranches.stream().map(branch -> new Branch(branch.name(),branch.commit().sha()))
                                .toList();

                    return new Repository(username, repo.name(), branches);
                }).toList();
    }
}
