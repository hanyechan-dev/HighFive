package com.jobPrize.service.consultant.consultantConsultingContent;

import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentUpdateDto;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.enumerate.DocumentType;

public interface ConsultantConsultingContentService {

	void createConsultantConsultingContent(ConsultantConsulting consultantConsulting, DocumentType documentType);
	void updateConsultantConsultingContent(ConsultantContentUpdateDto consultantContentUpdateDto);
}
