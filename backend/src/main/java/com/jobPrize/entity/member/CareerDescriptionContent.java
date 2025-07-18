package com.jobPrize.entity.member;

import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentUpdateDto;

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
@Table(name = "career_description_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerDescriptionContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAREER_DESCRIPTION_CONTENT_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAREER_DESCRIPTION_ID", nullable = false)
	private CareerDescription careerDescription;
	
	@Column(name="ITEM", nullable = false)
	private String item;
	
	@Column(name="CONTENT", nullable = false, length = 2000)
	private String content;

	public void updateContent(CareerDescriptionContentUpdateDto careerDescriptionContentUpdateDto) {
		this.item = careerDescriptionContentUpdateDto.getItem();
		this.content = careerDescriptionContentUpdateDto.getContent();
	}

	public static CareerDescriptionContent of(CareerDescription careerDescription, CareerDescriptionContentCreateDto careerDescriptionContentCreateDto) {
		return CareerDescriptionContent.builder()
			.careerDescription(careerDescription)
			.item(careerDescriptionContentCreateDto.getItem())
			.content(careerDescriptionContentCreateDto.getContent())
			.build();
	}
	
	

}
