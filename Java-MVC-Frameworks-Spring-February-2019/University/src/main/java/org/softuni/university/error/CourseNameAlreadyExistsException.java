package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "CourseNameAlreadyExistsException not found!")
public class CourseNameAlreadyExistsException extends RuntimeException {

    private int statusCode;

    public CourseNameAlreadyExistsException() {
        this.statusCode = 409;
    }

    public CourseNameAlreadyExistsException(String message) {
        super(message);
        this.statusCode = 409;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
