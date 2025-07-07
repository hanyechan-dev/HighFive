package com.jobPrize.entity.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.dto.member.coverLetter.CoverLetterCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterUpdateDto;
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
@Table(name = "cover_letter")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class CoverLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COVER_LETTER_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@Column(name="TITLE", nullable = false)
	private String title;
	
	@CreatedDate
	@Column(nullable = false, name="CREATED_DATE")
	private LocalDate createdDate;
	
	@Column(name = "cover_letter_vector", columnDefinition = "MEDIUMTEXT")
	private String coverLetterVector;
	
	@Column(name = "embedding_status")
	@Enumerated(EnumType.STRING)
	private EmbeddingStatus embeddingStatus = EmbeddingStatus.PENDING;
	
	@OneToMany(mappedBy = "coverLetter", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CoverLetterContent> coverLetterContents = new ArrayList<>();
	
	public void updateCoverLetter(CoverLetterUpdateDto coverLetterUpdateDto) {
		this.title = coverLetterUpdateDto.getTitle();
	}

	public static CoverLetter of(Member member, CoverLetterCreateDto coverLetterCreateDto) {
		return CoverLetter.builder()
			.member(member)
			.title(coverLetterCreateDto.getTitle())
			.embeddingStatus(EmbeddingStatus.PENDING)
			.build();	
	}
	
	public void updateVector(String vector) {
		this.coverLetterVector = vector;
	}
	
	public void updateEmbeddingStatus(EmbeddingStatus embeddingStatus) {
		this.embeddingStatus = embeddingStatus;
	}
	

}
