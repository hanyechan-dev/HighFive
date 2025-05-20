package com.jobPrize.validation.businessNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BusinessNumberValidator implements ConstraintValidator<BusinessNumber, String> {

    private static final String BUSINESSNUMBER_REGEX =
    		"\\d{10}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(BUSINESSNUMBER_REGEX);
    }
}
