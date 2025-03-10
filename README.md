# Zadanie_Atipera

## Description

A **Quarkus 3** and **Java 17** project that fetches a list of public GitHub repositories for a given user, including the owner's name and branches with the latest commit SHA.

## Endpoint

**GET** `/github/{user}/repoWithBranches`

Where `{user}` is the GitHub username.

## Example Response

```json
[
    {
        "name": "git-consortium",
        "ownerLogin": "octocat",
        "branches": [
            {
                "name": "master",
                "commit": { "sha": "b33a9c7c02ad..." }
            }
        ]
    },
    {
        "name": "Hello-World",
        "ownerLogin": "octocat",
        "branches": [
            {
                "name": "master",
                "commit": { "sha": "7fd1a60b01f..." }
            },
            {
                "name": "test",
                "commit": { "sha": "b3cbd5bbd7e..." }
            }
        ]
    }
]
```

## Requirements

- **Java 17**
- **Quarkus 3**

## Running the Application

```sh
./mvnw quarkus:dev
```

## Building the Application

```sh
./mvnw package
```

The built application will be available as a **JAR** in the `target/` directory.

## Testing the Endpoint

Using **cURL**:

```sh
curl -X GET http://localhost:8080/github/octocat/repoWithBranches
```

Or with **Postman**.

## License

This project is available under the MIT license.

