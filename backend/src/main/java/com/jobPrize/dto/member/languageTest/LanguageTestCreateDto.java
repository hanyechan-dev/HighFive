package com.jobPrize.dto.member.languageTest;

import java.time.LocalDate;

import com.jobPrize.validation.scoreAndGrade.ScoreAndGrade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;


//기본 생성자는 자동 생성됨. 역직렬화 문제 없음.
//나중에  Builder, 또는 AllArgsConstructor를 붙히게 될 경우 NoArgsConstructor 필수
@Getter
@ScoreAndGrade
public class LanguageTestCreateDto {

	@Size(max = 10, message = "언어는 10자 이하로 입력해야합니다.")
	@NotBlank(message = "언어는 필수로 입력해야합니다")
	private String languageType;

	@Size(max = 20, message = "시험명은 20자 이하로 입력해야합니다.")
	@NotBlank(message = "시험명은 필수로 입력해야합니다")
	private String testName;

	@Size(max = 20, message = "발행처는 20자 이하로 입력해야합니다.")
	@NotBlank(message = "발행처는 필수로 입력해야합니다")
	private String issuingOrg;

	@Size(max = 10, message = "등급은 10자 이하로 입력해야합니다.")
	private String grade;

	@Size(max = 10, message = "점수는 10자 이하로 입력해야합니다.")
	private String score;

	@Size(max = 20, message = "인증번호는 20자 이하로 입력해야합니다.")
	private String certificationNo;

	@NotNull(message = "취득일은 필수로 입력해야합니다.")
	@Past(message = "취득일은 현재 이하로 입력해야합니다.")
	private LocalDate acquisitionDate;
}
