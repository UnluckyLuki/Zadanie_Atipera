package org.example.models;

import java.util.List;

public class RepositoryWithBranches {
    public String name;
    public String ownerLogin;
    public List<Branch> branches;

    public RepositoryWithBranches(String name, String ownerLogin, List<Branch> branches) {
        this.name = name;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + '\"' +
                ", \"ownerLogin\": \"" + ownerLogin + '\"' +
                ", \"branches\": " + branches +
                '}';
    }
}
