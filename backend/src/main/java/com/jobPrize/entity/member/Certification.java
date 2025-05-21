package com.jobPrize.entity.member;

import java.time.LocalDate;

import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certification")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CERTIFICATION_ID", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;

	@Column(name = "CERTIFICATION_NAME", nullable = false)
	private String certificationName;

	@Column(name = "ISSUING_ORG", nullable = false)
	private String issuingOrg;

	@Column(name = "GRADE")
	private String grade;

	@Column(name = "SCORE")
	private String score;

	@Column(name = "CERTIFICATION_NO", nullable = false)
	private String certificationNo;

	@Column(name = "ACQUISITION_DATE", nullable = false)
	private LocalDate acquisitionDate;

	public void updateCertification(CertificationUpdateDto certificationUpdateDto) {
		this.certificationName = certificationUpdateDto.getCertificationName();
		this.issuingOrg = certificationUpdateDto.getIssuingOrg();
		this.grade = certificationUpdateDto.getGrade();
		this.score = certificationUpdateDto.getScore();
		this.certificationNo = certificationUpdateDto.getCertificationNo();
		this.acquisitionDate = certificationUpdateDto.getAcquisitionDate();
	}

	public static Certification of(Member member, CertificationCreateDto certificationCreateDto) {
		return Certification.builder()
			.member(member)
			.certificationName(certificationCreateDto.getCertificationName())
			.issuingOrg(certificationCreateDto.getIssuingOrg())
			.grade(certificationCreateDto.getGrade())
			.score(certificationCreateDto.getScore())
			.certificationNo(certificationCreateDto.getCertificationNo())
			.acquisitionDate(certificationCreateDto.getAcquisitionDate())
			.build();
	}

}
