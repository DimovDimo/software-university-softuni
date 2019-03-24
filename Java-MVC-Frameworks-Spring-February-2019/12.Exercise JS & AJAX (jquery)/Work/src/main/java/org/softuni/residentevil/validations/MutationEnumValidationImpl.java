package org.softuni.residentevil.validations;

import org.softuni.residentevil.domain.entities.Mutation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MutationEnumValidationImpl implements ConstraintValidator<MutationEnumValidation, Mutation> {

    @Override
    public boolean isValid(Mutation value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return true;
    }
}
