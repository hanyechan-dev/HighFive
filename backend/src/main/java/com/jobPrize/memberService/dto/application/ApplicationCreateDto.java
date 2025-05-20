package com.jobPrize.memberService.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApplicationCreateDto {
    
    @NotNull(message = "채용공고는 필수로 선택해야합니다.")
    private Long jobPostingId;
    
    @NotNull(message = "자기소개서는 필수로 선택해야합니다.")
    private Long coverLetterId;
    
    private Long careerDescriptionId;

}

