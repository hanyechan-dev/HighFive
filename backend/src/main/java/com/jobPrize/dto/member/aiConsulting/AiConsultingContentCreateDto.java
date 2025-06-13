package com.jobPrize.dto.member.aiConsulting;

import com.jobPrize.enumerate.DocumentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AiConsultingContentCreateDto {

	@NotBlank(message = "항목은 필수로 입력해야합니다.")
	@Size(max = 100, message = "항목은 100자 이하로 입력해야합니다.")
	private String item;
	
	@NotNull(message = "문서 종류는 필수로 입력해야합니다.")
	private DocumentType documentType;
    
	@NotBlank(message = "제목은 필수로 입력해야합니다.")
	@Size(max = 2000, message = "내용은 2000자 이하로 입력해야합니다.")
	private String content;

}
