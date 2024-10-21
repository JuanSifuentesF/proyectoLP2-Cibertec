package com.cib.biblioteca.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsbnValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIsbn {
    String message() default "ISBN debe tener 10 o 13 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
