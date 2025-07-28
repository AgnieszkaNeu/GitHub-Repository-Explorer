# GitHub Repo API

A Spring Boot application that fetches a GitHub user's public repositories (excluding forks) along with the names of their branches and the latest commit SHA for each branch.

## GitHub token

In the `application.properties` file, you need to provide your personal GitHub token in the `github.token` field.

## Endpoint API

### `GET /?username={username}`

Returns a list of the user's public repositories (excluding forks), in the following format:

✅Successful Response (HTTP 200)
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

❌Error Response (User Not Found – HTTP 404)
```json
{
  "status": 404,
  "message": "User not found: {username}"
}
```
