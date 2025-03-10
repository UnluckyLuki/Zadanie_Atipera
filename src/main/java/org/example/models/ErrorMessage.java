package org.example.models;

import jakarta.ws.rs.core.Response;

public class ErrorMessage {
    int status;
    String message;

    public ErrorMessage(Response.Status status, String message) {
        this.status = status.getStatusCode();
        this.message = message;
    }
    @Override
    public String toString() {
        return "{" +
                "\"status\": \"" + status + "\"" +
                ", \"message\": \"" + message + "\"" +
                "}";
    }
}
