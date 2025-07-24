package com.example.demo;

import com.example.demo.Entity.Repository;
import com.example.demo.Services.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitController {

    public RepositoryService repositoryService;

    public GitController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Repository>> getRepositoriesForUser (@RequestParam String username){
        return ResponseEntity.ok().body(repositoryService.getRepositoryWithBranches(username));
    }

}
