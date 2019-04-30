package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "ContactNotFoundException not found!")
public class ContactNotFoundException extends RuntimeException {

    private int statusCode;

    public ContactNotFoundException() {
        this.statusCode = 404;
    }

    public ContactNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
