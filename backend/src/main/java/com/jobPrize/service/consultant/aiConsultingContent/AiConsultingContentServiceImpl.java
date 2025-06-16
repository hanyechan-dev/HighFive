package com.jobPrize.service.consultant.aiConsultingContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.member.aiConsulting.AiConsultingContentCreateDto;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.enumerate.DocumentType;
import com.jobPrize.repository.consultant.aiConsultingContent.AiConsultingContentRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AiConsultingContentServiceImpl implements AiConsultingContentService {
	
	private final AiConsultingContentRepository aiConsultingContentRepository;
	
	

	@Override
	public void createAiConsultingContent(AiConsulting aiConsulting, AiConsultingContentCreateDto aiConsultingContentCreateDto) {
		AiConsultingContent aiConsultingContent = AiConsultingContent.builder()
				.aiConsulting(aiConsulting)
				.item(aiConsultingContentCreateDto.getItem())
				.documentType(DocumentType.valueOf(aiConsultingContentCreateDto.getDocumentType()))
				.content(aiConsultingContentCreateDto.getContent())
				.build();
		
		aiConsultingContentRepository.save(aiConsultingContent);
		
	}

}
