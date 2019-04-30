package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EnjoyDoNotCreateException not found!")
public class EnjoyDoNotCreateException extends RuntimeException {

    private int statusCode;

    public EnjoyDoNotCreateException() {
        this.statusCode = 400;
    }

    public EnjoyDoNotCreateException(String message) {
        super(message);
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
