package org.softuni.university.error;

import org.softuni.university.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ErrorConstants.DO_NOT_CREATE_EXCEPTION)
public class CourseDoNotCreateException extends RuntimeException {

    private int statusCode;

    public CourseDoNotCreateException() {
        this.statusCode = ErrorConstants.STATUS_CODE_400_DO_NOT_CREATE_EXCEPTION;
    }

    public CourseDoNotCreateException(String message) {
        super(message);
        this.statusCode = ErrorConstants.STATUS_CODE_400_DO_NOT_CREATE_EXCEPTION;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
