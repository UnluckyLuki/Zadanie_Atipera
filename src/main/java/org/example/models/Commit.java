package org.example.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    public String sha;

    @Override
    public String toString() {
        return "{" +
                "\"sha\": \"" + sha + '\"' +
                '}';
    }
}
