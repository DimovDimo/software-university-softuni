package org.softuni.residentevil.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CapitalsListValidationImpl implements ConstraintValidator<CapitalsListValidation, List> {
    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value.size() != 0;
    }
}
