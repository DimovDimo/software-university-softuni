package org.softuni.university.web.controllers;

import org.softuni.university.constants.ControllerConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleSqlException(Throwable e) {
        ModelAndView modelAndView = new ModelAndView(ControllerConstants.VIEW_ERROR);
        Throwable throwable = throwableGetCause(e);
        modelAndView.addObject(ControllerConstants.MESSAGE, throwable.getMessage());

        return modelAndView;
    }

    private Throwable throwableGetCause(Throwable e) {
        Throwable throwable = e;

        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        return throwable;
    }
}
