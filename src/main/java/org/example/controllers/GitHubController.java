package org.example.controllers;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.exceptions.UserNotFoundException;
import org.example.models.Branch;
import org.example.models.RepositoryWithBranches;
import org.example.services.GitHubBranchesService;
import org.example.services.GitHubService;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.List;
import java.util.stream.Collectors;


@Path("/github")
public class GitHubController {

    @Inject
    @RestClient
    GitHubService gitHubService;

    @Inject
    @RestClient
    GitHubBranchesService gitHubBranchesService;

    @GET
    @Path("/{user}/reposWithBranches")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<RepositoryWithBranches>> getRepositoriesWithBranches(@PathParam("user") String user) {
        return gitHubService.getRepositories(user)
                .onFailure(ClientWebApplicationException.class).recoverWithUni(exception ->{
                    if (((ClientWebApplicationException) exception).getResponse().getStatus() == 404) {
                        return Uni.createFrom().failure(new UserNotFoundException("User not found or no repositories available", Response.Status.NOT_FOUND));
                    }
                    return Uni.createFrom().failure(exception);
                })
                .onItem().transformToUni(repositories -> {
                    List<Uni<RepositoryWithBranches>> repositoriesWithBranches = repositories.stream()
                            .filter(repository -> !repository.fork)
                            .map(repository -> getBranches(repository.owner.login, repository.name)
                                    .onItem().transform(branches -> new RepositoryWithBranches(repository.name, repository.owner.login, branches)))
                            .collect(Collectors.toList());

                    return Uni.combine().all().unis(repositoriesWithBranches)
                            .combinedWith(results -> results.stream()
                                    .map(result -> (RepositoryWithBranches) result)
                                    .collect(Collectors.toList()));
                });
    }


    private Uni<List<Branch>> getBranches(String owner, String repo) {
        return gitHubBranchesService.getBranches(owner, repo);
    }
}
