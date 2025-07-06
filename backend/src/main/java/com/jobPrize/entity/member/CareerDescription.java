package com.jobPrize.entity.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.member.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionUpdateDto;
import com.jobPrize.enumerate.EmbeddingStatus;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career_description")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class CareerDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAREER_DESCRIPTION_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@Column(name="TITLE", nullable = false)
	private String title;
	
	@CreatedDate
	@Column(nullable = false, name="CREATED_DATE")
	private LocalDate createdDate;
	
	@OneToMany(mappedBy = "careerDescription", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CareerDescriptionContent> careerDescriptionContents = new ArrayList<>();
	
	@Column(name = "career_description_vector", columnDefinition = "MEDIUMTEXT")
	private String careerDescriptionVector;
	
	@Column(name = "embedding_status")
	@Enumerated(EnumType.STRING)
	private EmbeddingStatus embeddingStatus = EmbeddingStatus.PENDING;
	
	public void updateCareerDescription(CareerDescriptionUpdateDto careerDescriptionUpdateDto) {
		this.title = careerDescriptionUpdateDto.getTitle();
	}

	public static CareerDescription of(Member member, CareerDescriptionCreateDto careerDescriptionCreateDto) {
		return CareerDescription.builder()
			.member(member)
			.title(careerDescriptionCreateDto.getTitle())
			.embeddingStatus(EmbeddingStatus.PENDING)
			.build();
	}
	
	public void updateVector(String vector) {
		this.careerDescriptionVector = vector;
	}
	
	public void updateEmbeddingStatus(EmbeddingStatus embeddingStatus) {
		this.embeddingStatus = embeddingStatus;
	}

}
