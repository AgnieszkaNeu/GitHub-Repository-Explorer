package com.example.demo;

import com.example.demo.Entity.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
public class GitRepoApiTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUpStubs() {

        stubFor(get(urlEqualTo("/users/octocat")).willReturn(ok()));
        stubFor(get(urlEqualTo("/users/octocat/repos")).willReturn(okJson("""
                        [
                          {
                            "name": "demo-repo",
                            "owner": { "login": "octocat" },
                            "fork": false
                         }
                        ]
        """)));

        stubFor(get(urlEqualTo("/repos/octocat/demo-repo/branches")).willReturn(okJson("""
                [
                  {
                    "name": "main",
                    "commit": {
                      "sha": "abc123"
                    }
                  }
                ]
        """)));


    }

    @Test
    void shouldReturnRepositoriesWithBranches() {
        var response = testRestTemplate.getForEntity("/?username=octocat", Repository[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("octocat", response.getBody()[0].login());
        assertEquals("demo-repo", response.getBody()[0].repositoryName());
        assertEquals("abc123", response.getBody()[0].branches().get(0).sha());
        assertEquals("main", response.getBody()[0].branches().get(0).branchName());
    }
}
