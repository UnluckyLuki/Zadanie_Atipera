package org.example.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.ErrorMessage;

public class UserNotFoundException extends WebApplicationException {
    public UserNotFoundException(String message, Response.Status status) {
        super(Response.status(status)
                .entity(new ErrorMessage(status, message))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
