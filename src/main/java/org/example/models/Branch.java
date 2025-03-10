package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    public String name;
    public Commit commit;

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + '\"' +
                ", \"commit\": " + commit +
                '}';
    }
}
