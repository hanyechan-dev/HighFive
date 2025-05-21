package com.jobPrize.dto.member.certification;

import java.time.LocalDate;

import com.jobPrize.validation.scoreAndGrade.ScoreAndGrade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@ScoreAndGrade
public class CertificationCreateDto {

	@Size(max = 20, message = "자격증명은 20자 이하로 입력해야합니다.")
	@NotBlank(message = "자격증명은 필수로 입력해야합니다")
	private String certificationName;

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
