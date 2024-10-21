package com.cib.biblioteca.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<ValidIsbn, String> {
    @Override
    public void initialize(ValidIsbn constraintAnnotation) {
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        return isbn != null && (isbn.length() == 10 || isbn.length() == 13) && isbn.matches("\\d+");
    }
}
