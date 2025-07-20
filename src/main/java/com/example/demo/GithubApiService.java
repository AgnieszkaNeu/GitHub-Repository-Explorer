package com.example.demo;

import com.example.demo.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GithubApiService {

    private final static String API_GITHUB_COM = "https://api.github.com";
    @Value("${github.token}")
    private String token;
    private final RestTemplate restTemplate;

    public GithubApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getUser(String username){

        String request = API_GITHUB_COM + "/users/" + username;
        HttpEntity<String> httpEntity = this.getHttpEntity();

        try {
            restTemplate.exchange(request, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User not found: " + username);
        }
    }

    public ResponseEntity<List> getUserRepositories(String username){

        String request = API_GITHUB_COM + "/users/" + username + "/repos";
        HttpEntity<String> httpEntity = this.getHttpEntity();

        return restTemplate.exchange(request, HttpMethod.GET, httpEntity, List.class);
    }

    public ResponseEntity<List> getBranch (String owner, String repo){

        String request = API_GITHUB_COM + "/repos/" + owner + "/" + repo + "/branches";
        HttpEntity<String> httpEntity = this.getHttpEntity();

        return restTemplate.exchange(request, HttpMethod.GET, httpEntity, List.class);

    }

    private HttpEntity<String> getHttpEntity(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");

        return new HttpEntity<String>(httpHeaders);
    }
}
