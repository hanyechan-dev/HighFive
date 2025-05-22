package com.jobPrize.service.consultant.consultantConsultingContent;

import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentUpdateDto;
import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.consultant.ConsultantConsulting;

public interface ConsultantConsultingContentService {

	void createConsultantConsultingContent(ConsultantConsulting consultantConsulting, CommonEnum.DocumentType documentType);
	void updateConsultantConsultingContent(ConsultantContentUpdateDto consultantContentUpdateDto);
}
