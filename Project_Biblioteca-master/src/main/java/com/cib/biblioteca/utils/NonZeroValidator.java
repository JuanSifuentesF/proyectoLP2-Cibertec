package com.cib.biblioteca.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NonZeroValidator implements ConstraintValidator<NonZero, Number> {
    @Override
    public void initialize(NonZero constraintAnnotation) {
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
        if (number == null) {
            return false;
        }
        if (number instanceof Integer) {
            return ((Integer) number) > 0;
        } else if (number instanceof Double) {
            return ((Double) number) > 0.0;
        }
        return false;
    }
}
