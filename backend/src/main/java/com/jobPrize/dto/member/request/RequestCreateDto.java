package com.jobPrize.dto.member.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateDto {
    
    @Size(max = 20, message = "희망 직무는 20자 이하로 입력해야합니다.")
    private String targetJob;

    @Size(max = 20, message = "희망 기업은 20자 이하로 입력해야합니다.")
    private String targetCompanyName;
    
    private String ConsultingType;
    
    @NotNull(message = "자기소개서는 필수로 선택해야합니다.")
    private Long coverLetterId;
    
    private Long careerDescriptionId;

}

