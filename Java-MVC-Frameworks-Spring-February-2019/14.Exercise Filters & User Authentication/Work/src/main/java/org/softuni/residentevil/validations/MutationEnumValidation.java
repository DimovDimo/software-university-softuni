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
@Constraint(validatedBy = MutationEnumValidationImpl.class)
@ReportAsSingleViolation
public @interface MutationEnumValidation {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Cannot be null. Should hold one of the following values: ZOMBIE, T_078_TYRANT, GIANT_SPIDER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
