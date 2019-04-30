package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "ModuleNotFoundException not found!")
public class ModuleNotFoundException extends RuntimeException {

    private int statusCode;

    public ModuleNotFoundException() {
        this.statusCode = 404;
    }

    public ModuleNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
