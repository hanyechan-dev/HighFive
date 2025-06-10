package com.jobPrize.validation.minForString;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class MinForStringValidator implements ConstraintValidator<MinForString, String> {

    private int minValue;

    @Override
    public void initialize(MinForString constraintAnnotation) {
        this.minValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches("\\d+")) return false;

        try {
            int intValue = Integer.parseInt(value);
            return intValue >= minValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}