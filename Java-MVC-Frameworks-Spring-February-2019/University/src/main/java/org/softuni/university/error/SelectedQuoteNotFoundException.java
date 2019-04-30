package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "SelectedQuoteNotFoundException not found!")
public class SelectedQuoteNotFoundException extends RuntimeException {

    private int statusCode;

    public SelectedQuoteNotFoundException() {
        this.statusCode = 404;
    }

    public SelectedQuoteNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
