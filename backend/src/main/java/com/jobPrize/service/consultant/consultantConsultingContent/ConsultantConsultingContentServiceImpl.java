package com.jobPrize.service.consultant.consultantConsultingContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentUpdateDto;
import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.ConsultantConsultingContent;
import com.jobPrize.repository.consultant.consultantConsultingContent.ConsultantConsultingContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantConsultingContentServiceImpl implements ConsultantConsultingContentService {

    private final ConsultantConsultingContentRepository consultantConsultingContentRepository;

    @Override
    public void createConsultantConsultingContent(ConsultantConsulting consultantConsulting, CommonEnum.DocumentType documentType) {

        ConsultantConsultingContent consultantConsultingContent = ConsultantConsultingContent.builder()
                .consultantConsulting(consultantConsulting)
                .item(null)
                .content(null)
                .documentType(documentType)
                .build();

        consultantConsultingContentRepository.save(consultantConsultingContent);
    }

	@Override
	public void updateConsultantConsultingContent(ConsultantContentUpdateDto consultantContentUpdateDto) {
		Long consultantConsultingContentId=consultantContentUpdateDto.getId();
		
		ConsultantConsultingContent consultantConsultingContent=consultantConsultingContentRepository.findById(consultantConsultingContentId)
				.orElseThrow(()-> new IllegalArgumentException("해당 컨설턴트 컨설팅 내용이 존재하지 않습니다."));

		consultantConsultingContent.updateContent(consultantContentUpdateDto.getItem(), consultantContentUpdateDto.getContent());
		
	}
}
