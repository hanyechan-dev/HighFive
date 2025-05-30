package com.jobPrize.dto.consultant.consultantConsultingContent;


import com.jobPrize.enumerate.DocumentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ConsultantContentUpdateDto {
	
	@NotNull(message = "컨설턴트 컨설팅 내용 id는 필수입니다.")
	private Long id;
	
	@Size(max = 100, message = "항목은 50자 이하로 입력해야합니다.")
	@NotBlank(message = "항목은 필수로 입력해야합니다")
    private String item;
	
	@Size(max = 2000, message = "내용은 50자 이하로 입력해야합니다.")
	@NotBlank(message = "내용은 필수로 입력해야합니다")
    private String content;
    
    @NotNull(message = "문서 구분은 필수입니다.")
    private DocumentType documentType;
}
