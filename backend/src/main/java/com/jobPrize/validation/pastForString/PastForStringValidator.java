package com.jobPrize.validation.pastForString;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PastForStringValidator implements ConstraintValidator<PastForString, String> {

    private DateTimeFormatter formatter;

    @Override
    public void initialize(PastForString constraintAnnotation) {
        this.formatter = DateTimeFormatter.ofPattern(constraintAnnotation.pattern());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) return false;

        try {
            LocalDate date = LocalDate.parse(value, formatter);
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}