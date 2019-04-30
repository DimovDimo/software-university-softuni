package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Inclusion not found!")
public class InclusionNotFoundException extends RuntimeException {

    private int statusCode;

    public InclusionNotFoundException() {
        this.statusCode = 404;
    }

    public InclusionNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
