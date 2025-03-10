package org.example.services;


import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.models.Repository;

import java.util.List;

@Path("/users/{user}/repos")
@RegisterRestClient
public interface GitHubService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Repository>> getRepositories(@PathParam("user") String user);
}
