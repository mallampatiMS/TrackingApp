package com.tracking.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomerSlugValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCustomerSlug {
    String message() default "Invalid customer slug. Must be in slug-case/kebab-case.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
