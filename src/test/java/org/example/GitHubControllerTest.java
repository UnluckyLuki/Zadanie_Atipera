package org.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GitHubControllerTest {

    @Test
    public void testGetRepositoriesWithBranchesHappyPath() {
        String testUser = "octocat";
        given()
                .pathParam("user", testUser)
                .when()
                .get("/github/{user}/reposWithBranches")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("$.size()", greaterThan(0))
                .body("[0].name", not(emptyString()))
                .body("[0].ownerLogin", equalTo(testUser))
                .body("[0].branches", not(empty()))
                .body("[0].branches[0].name", not(emptyString()))
                .body("[0].branches[0].commit.sha", matchesPattern("^[0-9a-f]{40}$"));
    }
}