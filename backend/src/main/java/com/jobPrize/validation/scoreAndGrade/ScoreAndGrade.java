package com.jobPrize.validation.scoreAndGrade;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ScoreAndGradeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ScoreAndGrade {
    String message() default "점수와 등급 중 하나는 필수 입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
