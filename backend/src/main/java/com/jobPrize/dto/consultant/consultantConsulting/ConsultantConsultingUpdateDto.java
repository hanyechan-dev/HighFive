package com.jobPrize.dto.consultant.consultantConsulting;

import java.util.List;

import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentUpdateDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class ConsultantConsultingUpdateDto {
	
	@NotNull(message = "저장시 id는 필수입니다.")
	private Long consultantConsultingid;
	
	List<ConsultantContentUpdateDto> ConsultantContentUpdateDtos;
}
