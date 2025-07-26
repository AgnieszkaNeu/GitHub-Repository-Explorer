package com.example.demo.Services;

import com.example.demo.Entity.GithubApiBranch;
import com.example.demo.Entity.GithubApiRepository;
import com.example.demo.Exceptions.GithubApiException;
import com.example.demo.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubApiService {

    private final static String API_GITHUB_COM = "https://api.github.com";
    @Value("${github.token}")
    private String token;
    private final RestClient restClient;

    public GithubApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void checkIfUserExists(String username){
        String request = API_GITHUB_COM + "/users/" + username;
        restClient.get()
                    .uri(request)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new UserNotFoundException(username);
                    }).toBodilessEntity();
    }

    public List<GithubApiRepository> getUserRepositories(String username){
        String request = API_GITHUB_COM + "/users/" + username + "/repos";
        return restClient.get()
                .uri(request)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req,res) -> {
                    throw new GithubApiException(res.getStatusText());
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<GithubApiBranch> getBranch (String owner, String repo){
        String request = API_GITHUB_COM + "/repos/" + owner + "/" + repo + "/branches";
        return restClient.get()
                .uri(request)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
