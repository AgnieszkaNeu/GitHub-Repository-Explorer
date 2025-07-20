package com.example.demo;

import com.example.demo.Entity.Branch;
import com.example.demo.Entity.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitRepoApiApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldFetchNonForkRepositoriesWithBranches() {

		String testUsername = "octocat";

		String url = "http://localhost:" + port + "/?username=" + testUsername;

		ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Repository[] repositories = response.getBody();
		assertThat(repositories).isNotNull();
		assertThat(repositories.length).isGreaterThan(0);

		for (Repository repo : repositories) {
			assertThat(repo.getLogin()).isNotBlank();
			assertThat(repo.getName()).isNotBlank();

			List<Branch> branches = repo.getBranches();
			if (branches != null) {
				assertThat(branches).allSatisfy(branch -> {
					assertThat(branch.getName()).isNotBlank();
					assertThat(branch.getSha()).isNotBlank();
				});
			}
		}
	}

}
