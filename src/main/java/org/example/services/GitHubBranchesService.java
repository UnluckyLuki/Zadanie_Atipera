package org.example.services;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.models.Branch;

import java.util.List;


@Path("/repos/{owner}/{repo}/branches")
@RegisterRestClient
public interface GitHubBranchesService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Branch>> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
