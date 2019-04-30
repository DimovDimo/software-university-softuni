package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "QuoteNotFoundException not found!")
public class QuoteNotFoundException extends RuntimeException {

    private int statusCode;

    public QuoteNotFoundException() {
        this.statusCode = 404;
    }

    public QuoteNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
