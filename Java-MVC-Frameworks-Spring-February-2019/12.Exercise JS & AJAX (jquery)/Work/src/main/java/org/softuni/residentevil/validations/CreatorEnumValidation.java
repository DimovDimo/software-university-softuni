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
@Constraint(validatedBy = CreatorEnumValidationImpl.class)
@ReportAsSingleViolation
public @interface CreatorEnumValidation {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Should be either Corp or corp.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
