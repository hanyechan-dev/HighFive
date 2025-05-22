package com.jobPrize.service.consultant.consultantConsulting;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentCreateDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentResponseDto;
import com.jobPrize.dto.consultant.consultantConsultingContent.ConsultantContentUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.Consultant;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.ConsultantConsultingContent;
import com.jobPrize.repository.consultant.aiConsulting.AiConsultingRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.repository.consultant.consultantConsulting.ConsultantConsultingRepository;
import com.jobPrize.service.consultant.consultantConsultingContent.ConsultantConsultingContentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantConsultingServiceImpl implements ConsultantConsultingService {
	
	private final ConsultantConsultingRepository consultantConsultingRepository;
	private final AiConsultingRepository aiConsultingRepository;
	private final ConsultantRepository consultantRepository;
	private final ConsultantConsultingContentService consultantConsultingContentService;
	
	@Override
	public void approveConsulting(Long id, UserType userType, Long aiConsultingId) {
		if(userType!=UserType.컨설턴트회원) {
			throw new AccessDeniedException("승인은 컨설턴트만 가능 합니다.");
		}
	    AiConsulting aiConsulting = aiConsultingRepository.findById(aiConsultingId)
	        .orElseThrow(() -> new IllegalArgumentException("해당 AI 컨설팅이 존재하지 않습니다."));

	    Consultant consultant = consultantRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("해당 컨설턴트가 존재하지 않습니다."));

	    if (aiConsulting.getConsultantConsulting() != null) {
	        throw new IllegalStateException("이미 승인된 컨설팅입니다.");
	    }

	    ConsultantConsulting consultantConsulting = ConsultantConsulting
	    	.builder()
	        .aiConsulting(aiConsulting)
	        .consultant(consultant)
	        .type(aiConsulting.getType())
	        .build();

	    consultantConsultingRepository.save(consultantConsulting);
	    
	    List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
	    
	    for(AiConsultingContent aiConsultingContent : aiConsultingContents) {
	    	consultantConsultingContentService.createConsultantConsultingContent(consultantConsulting, aiConsultingContent.getDocumentType());
	    }
	    
	    
	}
	
	@Override
	public void updateConsultantConsulting(Long id, ConsultantConsultingUpdateDto consultantConsultingUpdateDto) {
	
		ConsultantConsulting consultantConsulting = consultantConsultingRepository.findById(consultantConsultingUpdateDto.getConsultantConsultingid())
				.orElseThrow(()-> new IllegalArgumentException("해당 컨설턴트 컨설팅이 존재하지 않습니다."));
		
		if(!consultantConsulting.getConsultant().getId().equals(id)) {
			throw new AccessDeniedException("해당 컨설팅을 수정할 권한이 없습니다.");
		}
		
		
		List<ConsultantContentUpdateDto> consultantContentUpdateDtos = consultantConsultingUpdateDto.getConsultantContentUpdateDtos();
		
		for(ConsultantContentUpdateDto consultantContentUpdateDto : consultantContentUpdateDtos) {
			consultantConsultingContentService.updateConsultantConsultingContent(consultantContentUpdateDto);
		}
		
		
	}
	 
	
	@Override
	public void completeConsulting(Long consultantId, Long consultantConsultingId) {
	    ConsultantConsulting consultantConsulting = consultantConsultingRepository.findById(consultantConsultingId)
	        .orElseThrow(() -> new IllegalArgumentException("해당 컨설팅 이력이 존재하지 않습니다."));
	    
	    if (!consultantConsulting.getConsultant().getId().equals(consultantId)) {
	    	throw new AccessDeniedException("완료 권한이 없습니다.");
	    }

	    if (consultantConsulting.getCompletedDate() != null) {
	        throw new IllegalStateException("이미 완료된 컨설팅입니다.");
	    }

	    consultantConsulting.complete();
	    consultantConsultingRepository.save(consultantConsulting);
	}


	// 컨설팅 현황 관리 페이지 
	 @Override
	 public Page<ConsultantConsultingSummaryDto> readConsultantConsultingPageByCondition(Long consultantId, Pageable pageable) {
		 Page<ConsultantConsulting> entityPage =
				    consultantConsultingRepository.findWithAllConsultantConsultingByConsultantId(consultantId, pageable);


	        List<ConsultantConsultingSummaryDto> dtoList = new ArrayList<>();

	        for (ConsultantConsulting consultantConsulting : entityPage.getContent()) {
	            ConsultantConsultingSummaryDto consultantConsultingSummaryDto = toSummaryDto(consultantConsulting);
	            dtoList.add(consultantConsultingSummaryDto);
	        }


	        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements()); //전체 데이터 개수 유지해서 프론트에 알려줌
	    }


	    
	    @Override
	    public ConsultantEditDetailResponseDto readEditDetail(Long consultantId,Long consultantConsultingId) {
	        ConsultantConsulting consultantConsulting = consultantConsultingRepository
	            .findWithConsultantConsultingContentsByConsultantConsultingId(consultantConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 컨설팅 이력이 존재하지 않습니다."));
	        
	        if(!consultantConsulting.getConsultant().getId().equals(consultantId)) {
	        	throw new AccessDeniedException("조회 권한이 없습니다.");
	        }

	        AiConsulting aiConsulting = consultantConsulting.getAiConsulting();

	        List<AiContentResponseDto> aiContents = new ArrayList<>();
	        for (AiConsultingContent aiConsultingContent : aiConsulting.getAiConsultingContents()) {
	            AiContentResponseDto aiContentResponseDto = AiContentResponseDto
	            		.builder()
	                .item(aiConsultingContent.getItem())
	                .content(aiConsultingContent.getContent())
	                .build();
	            aiContents.add(aiContentResponseDto);
	        }

	        List<ConsultantContentResponseDto> consultantContents = new ArrayList<>();
	        for (ConsultantConsultingContent consultantConsultingContent : consultantConsulting.getConsultantConsultingContents()) {
	            ConsultantContentResponseDto consultantCommentResponseDto = ConsultantContentResponseDto
	            	.builder()
	                .item(consultantConsultingContent.getItem())
	                .content(consultantConsultingContent.getContent())
	                .documentType(consultantConsultingContent.getDocumentType())
	                .build();
	            consultantContents.add(consultantCommentResponseDto);
	        }

	        return ConsultantEditDetailResponseDto
	        	.builder()
	            .targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
	            .targetJob(aiConsulting.getRequest().getTargetJob())
	            .requestedDate(aiConsulting.getRequest().getCreatedDate())
	            .createdDate(consultantConsulting.getCreatedDate())
	            .resume(aiConsulting.getRequest().getResumeJson())
	            .careerDescription(aiConsulting.getRequest().getCareerDescriptionJson())
	            .coverLetter(aiConsulting.getRequest().getCoverLetterJson())
	            .aiContents(aiContents)
	            .consultantContents(consultantContents)
	            .build();
	    }

	    
	    @Override
	    public ConsultantFeedBackDetailResponseDto readFeedbackDetail(Long consultantId, Long consultantConsultingId) {
	        ConsultantConsulting consultantConsulting = consultantConsultingRepository
	            .findWithConsultantConsultingContentsByConsultantConsultingId(consultantConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 컨설팅 이력이 존재하지 않습니다."));
	        
	        if (!consultantConsulting.getConsultant().getId().equals(consultantId)) {
	            throw new AccessDeniedException("조회 권한이 없습니다.");
	        }

	        AiConsulting aiConsulting = consultantConsulting.getAiConsulting();

	        List<AiContentResponseDto> aiContents = new ArrayList<>();
	        for (AiConsultingContent aiConsultingContent : aiConsulting.getAiConsultingContents()) {
	            AiContentResponseDto aiContentResponseDto = AiContentResponseDto
	                .builder()
	                .item(aiConsultingContent.getItem())
	                .content(aiConsultingContent.getContent())
	                .build();
	            aiContents.add(aiContentResponseDto);
	        }

	        List<ConsultantContentResponseDto> consultantContents = new ArrayList<>();
	        for (ConsultantConsultingContent consultantConsultingContent : consultantConsulting.getConsultantConsultingContents()) {
	            ConsultantContentResponseDto consultantCommentResponseDto = ConsultantContentResponseDto
	                .builder()
	                .item(consultantConsultingContent.getItem())
	                .content(consultantConsultingContent.getContent())
	                .documentType(consultantConsultingContent.getDocumentType())
	                .build();
	            consultantContents.add(consultantCommentResponseDto);
	        }

	        return ConsultantFeedBackDetailResponseDto
	            .builder()
	            .targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
	            .targetJob(aiConsulting.getRequest().getTargetJob())
	            .requestedDate(aiConsulting.getRequest().getCreatedDate())
	            .createdDate(consultantConsulting.getCreatedDate())
	            .resume(aiConsulting.getRequest().getResumeJson())
	            .careerDescription(aiConsulting.getRequest().getCareerDescriptionJson())
	            .coverLetter(aiConsulting.getRequest().getCoverLetterJson())
	            .aiContents(aiContents)
	            .consultantContents(consultantContents)
	            .build();
	    }
	    
	    
	    
	 // 컨설팅 현황 관리 페이지 
	    private ConsultantConsultingSummaryDto toSummaryDto(ConsultantConsulting consultantConsulting) 	 {
	        return ConsultantConsultingSummaryDto
	        		.builder()
	                .consultantConsultingId(consultantConsulting.getId())
	                .userName(consultantConsulting.getAiConsulting().getRequest().getMember().getUser().getName())
	                .targetJob(consultantConsulting.getAiConsulting().getRequest().getTargetJob())
	                .targetCompanyName(consultantConsulting.getAiConsulting().getRequest().getTargetCompanyName())
	                .requestedDate(consultantConsulting.getAiConsulting().getRequestedDate())
	                .consultingType(consultantConsulting.getType())
	                .completed(consultantConsulting.getCompletedDate() != null)	                
	                .build();
	    }

	    

	    
	}