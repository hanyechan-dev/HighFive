package com.jobPrize.entity.member;

import com.jobPrize.dto.member.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentUpdateDto;

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
@Table(name = "cover_letter_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoverLetterContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COVER_LETTER_CONTENT_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COVER_LETTER_ID", nullable = false)
	private CoverLetter coverLetter;
	
	@Column(name="ITEM", nullable = false)
	private String item;
	
	@Column(name="CONTENT", nullable = false)
	private String content;
	
	public void updateContent(CoverLetterContentUpdateDto coverLetterContentUpdateDto) {
		this.item = coverLetterContentUpdateDto.getItem();
		this.content = coverLetterContentUpdateDto.getContent();
	}
	
    public static CoverLetterContent of(CoverLetter coverLetter, CoverLetterContentCreateDto coverLetterContentCreateDto) {
    	return CoverLetterContent.builder()
    			.coverLetter(coverLetter)
    			.item(coverLetterContentCreateDto.getItem())
    			.content(coverLetterContentCreateDto.getContent())
    			.build();

    }

}