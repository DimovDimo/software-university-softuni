package org.softuni.university.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CourseDoNotCreateException not found!")
public class CourseDoNotCreateException extends RuntimeException {

    private int statusCode;

    public CourseDoNotCreateException() {
        this.statusCode = 400;
    }

    public CourseDoNotCreateException(String message) {
        super(message);
        this.statusCode = 400;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
