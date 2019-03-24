package org.softuni.residentevil.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = DateValidationImpl.class)
@ReportAsSingleViolation
public @interface DateValidation {

    String message() default "Date, should be before the “today” date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
