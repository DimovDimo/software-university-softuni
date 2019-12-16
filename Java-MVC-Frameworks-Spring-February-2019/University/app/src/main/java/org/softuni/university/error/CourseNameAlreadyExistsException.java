package org.softuni.university.error;

import org.softuni.university.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = ErrorConstants.NAME_ALREADY_EXISTS_EXCEPTION)
public class CourseNameAlreadyExistsException extends RuntimeException {

    private int statusCode;

    public CourseNameAlreadyExistsException() {
        this.statusCode = ErrorConstants.STATUS_CODE_409_NAME_ALREADY_EXISTS_EXCEPTION;
    }

    public CourseNameAlreadyExistsException(String message) {
        super(message);
        this.statusCode = ErrorConstants.STATUS_CODE_409_NAME_ALREADY_EXISTS_EXCEPTION;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
