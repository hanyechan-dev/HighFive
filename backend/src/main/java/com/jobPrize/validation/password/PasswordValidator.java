package com.jobPrize.validation.password;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).{10,20}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(PASSWORD_REGEX);
    }
}