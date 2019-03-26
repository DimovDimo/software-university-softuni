package org.softuni.residentevil.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidationImpl implements ConstraintValidator<DateValidation, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate dateNow = LocalDate.now();
        if (value == null) {
            return false;
        }

        return dateNow.compareTo(value) > 0;

    }
}
