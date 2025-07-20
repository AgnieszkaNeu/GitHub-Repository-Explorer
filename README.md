# GitHub Repo API

A Spring Boot application used to fetch a GitHub user's public repositories.

## GitHub token

In the `application.properties` file, you need to provide your personal GitHub token in the `github.token` field.

## Endpoint API

### `GET /?username={username}`

Returns a list of the user's public repositories (excluding forks), in the following format:

```json
[
  {
    "login": "octocat",
    "name": "Hello-World",
    "branches": [
      {
        "name": "main",
        "sha": "6dcb09b..."
      },
      {
        "name": "dev",
        "sha": "9a4e1f4..."
      }
    ]
  },
  ...
]
```

