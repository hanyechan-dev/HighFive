package com.jobPrize.entity.memToCon;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.member.request.RequestCreateDto;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ConsultingType;
import com.jobPrize.enumerate.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQUEST_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@Column(name = "TARGET_JOB")
	private String targetJob;
	
	@Column(name = "TARGET_COMPANY_NAME")
	private String targetCompanyName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CONSULTING_TYPE", nullable = false)
	private ConsultingType consultingType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CONSULTING_STATUS", nullable = false)
	private RequestStatus requestStatus;

	@Column(name = "resume_json", columnDefinition = "TEXT", nullable = false)
	private String resumeJson;

	@Column(name = "career_description_json", columnDefinition = "TEXT")
	private String careerDescriptionJson;
	
	@Column(name = "cover_letter_json", columnDefinition = "TEXT", nullable = false)
	private String coverLetterJson;
	
	@CreatedDate
	@Column(nullable = false, name="CREATED_DATE")
	private LocalDate createdDate;

	@OneToOne(mappedBy = "request", fetch = FetchType.LAZY)
	private AiConsulting aiConsulting;

	public static Request of(Member member, RequestCreateDto requestCreateDto, RequestStatus requestStatus, String resumeJson, String careerDescriptionJson, String coverLetterJson) {
		return Request.builder()
			.member(member)
			.targetJob(requestCreateDto.getTargetJob())
			.targetCompanyName(requestCreateDto.getTargetCompanyName())
			.requestStatus(requestStatus)
			.resumeJson(resumeJson)
			.careerDescriptionJson(careerDescriptionJson)
			.coverLetterJson(coverLetterJson)
			.consultingType(requestCreateDto.getType())
			.build();
	}
	
	public void updateRequestStatus (RequestStatus requestStatus) {
		this.requestStatus =requestStatus;
	}

}
