package com.Erp_System.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValid implements ConstraintValidator<ImageValid, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // Null or blank check
        if (s == null || s.isBlank()) {
            return false;
        }

        // Example validation logic: Check for valid image extensions
        return s.endsWith(".jpg") || s.endsWith(".png") || s.endsWith(".jpeg");
    }
}
