package com.jobPrize.dto.member.certification;

import java.time.LocalDate;

import com.jobPrize.entity.member.Certification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponseDto {
	
	private Long id;

	private String certificationName;

	private String issuingOrg;

	private String grade;

	private String score;

	private String certificationNo;

	private LocalDate acquisitionDate;
	
	public static CertificationResponseDto from(Certification certification) {
		return CertificationResponseDto.builder()
				.id(certification.getId())
				.certificationName(certification.getCertificationName())
				.issuingOrg(certification.getIssuingOrg())
				.grade(certification.getGrade())
				.score(certification.getScore())
				.certificationNo(certification.getCertificationNo())
				.acquisitionDate(certification.getAcquisitionDate())
				.build();
	}
}
