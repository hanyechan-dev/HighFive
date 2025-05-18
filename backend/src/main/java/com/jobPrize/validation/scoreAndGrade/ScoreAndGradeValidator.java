package com.jobPrize.validation.scoreAndGrade;

import java.lang.reflect.Field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScoreAndGradeValidator implements ConstraintValidator<ScoreAndGrade,Object>{

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) return true;
		
		try {
            Field gradeField = value.getClass().getDeclaredField("grade");
            Field scoreField = value.getClass().getDeclaredField("score");

            gradeField.setAccessible(true);
            scoreField.setAccessible(true);

            Object grade = gradeField.get(value);
            Object score = scoreField.get(value);

            boolean hasGrade = grade != null && !grade.toString().isBlank();
            boolean hasScore = score != null && !score.toString().isBlank();

            return hasGrade || hasScore;

        } catch (NoSuchFieldException | IllegalAccessException e) {
        	throw new RuntimeException("ScoreAndGradeValidator: 'grade' 또는 'score' 필드가 없습니다.", e);
        }
	}
	

}
