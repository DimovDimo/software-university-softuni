package org.softuni.university.error;

import org.softuni.university.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ErrorConstants.NOT_FOUND_EXCEPTION)
public class InclusionNotFoundException extends RuntimeException {

    private int statusCode;

    public InclusionNotFoundException() {
        this.statusCode = ErrorConstants.STATUS_CODE_404_NOT_FOUND_EXCEPTION;
    }

    public InclusionNotFoundException(String message) {
        super(message);
        this.statusCode = ErrorConstants.STATUS_CODE_404_NOT_FOUND_EXCEPTION;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
