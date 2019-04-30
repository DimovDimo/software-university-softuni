package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CampNotFoundException not found!")
public class CampNotFoundException extends RuntimeException {

    private int statusCode;

    public CampNotFoundException() {
        this.statusCode = 404;
    }

    public CampNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
