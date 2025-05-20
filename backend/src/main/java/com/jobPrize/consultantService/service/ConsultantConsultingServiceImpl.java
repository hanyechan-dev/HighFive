package com.jobPrize.consultantService.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.consultantService.dto.AiContentResponseDto;
import com.jobPrize.consultantService.dto.ConsultantConsultingSummaryDto;
import com.jobPrize.consultantService.dto.ConsultantContentRequestDto;
import com.jobPrize.consultantService.dto.ConsultantContentResponseDto;
import com.jobPrize.consultantService.dto.ConsultantEditDetailResponseDto;
import com.jobPrize.consultantService.dto.ConsultantFeedBackDetailResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.Consultant;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.ConsultantConsultingContent;
import com.jobPrize.repository.consultant.aiConsulting.AiConsultingRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.repository.consultant.consultantConsulting.ConsultantConsultingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultantConsultingServiceImpl implements ConsultantConsultingService {
	
	private final ConsultantConsultingRepository consultantConsultingRepository;
	private final AiConsultingRepository aiConsultingRepository;
	private final ConsultantRepository consultantRepository;
	
	@Override
	@Transactional
	public void approveConsulting(Long consultantId, Long aiConsultingId, UserType userType) {
		if(userType!=UserType.컨설턴트회원) {
			throw new AccessDeniedException("승인은 컨설턴트만 가능 합니다.");
		}
	    AiConsulting aiConsulting = aiConsultingRepository.findById(aiConsultingId)
	        .orElseThrow(() -> new IllegalArgumentException("해당 AI 컨설팅이 존재하지 않습니다."));

	    Consultant consultant = consultantRepository.findById(consultantId)
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
	}
	
	@Override
	@Transactional
	public void saveConsultingContents(Long consultantId, Long consultantConsultingId, List<ConsultantContentRequestDto> dtoList) {

	    if (dtoList == null || dtoList.isEmpty()) {
	        throw new IllegalArgumentException("저장할 항목이 없습니다.");
	    }

	    ConsultantConsulting consultantConsulting = consultantConsultingRepository.findById(consultantConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 컨설팅 이력이 존재하지 않습니다."));
	    
	    if(!consultantConsulting.getConsultant().getId().equals(consultantId)){
	    	throw new AccessDeniedException("저장 권한이 없습니다.");
	    }
	    
	    
	    if(consultantConsulting.getCompletedDate()!=null) {
	    	throw new IllegalArgumentException("이미 완료된 컨설팅입니다.");
	    }

	    List<ConsultantConsultingContent> contentList = consultantConsulting.getConsultantConsultingContents();

	    for (ConsultantContentRequestDto consultantContentRequestDto : dtoList) {
	        boolean isUpdated = false;

	        for (ConsultantConsultingContent consultantConsultingContent : contentList) {
	            if (consultantConsultingContent.getItem().equals(consultantContentRequestDto.getItem()) &&
	            	consultantConsultingContent.getDocumentType().equals(consultantContentRequestDto.getDocumentType())) {
	            	consultantConsultingContent.updateContent(consultantContentRequestDto.getItem(), consultantContentRequestDto.getContent());
	                isUpdated = true;
	                break;
	            }
	        }

	        if (!isUpdated) {
	            ConsultantConsultingContent newContent = ConsultantConsultingContent
	            		.builder()
	                    .consultantConsulting(consultantConsulting)
	                    .item(consultantContentRequestDto.getItem())
	                    .content(consultantContentRequestDto.getContent())
	                    .documentType(consultantContentRequestDto.getDocumentType())
	                    .build();

	            contentList.add(newContent);
	        }
	    }
	}


	@Override
	@Transactional(readOnly = true)
	public List<ConsultantContentResponseDto> getConsultingContentResponses(Long consultantId, Long consultantConsultingId) {
	    ConsultantConsulting consulting = consultantConsultingRepository.findById(consultantConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 컨설팅 이력이 존재하지 않습니다."));
	    
	    
	    if(!consulting.getConsultant().getId().equals(consultantId)){
	    	throw new AccessDeniedException("조회 권한이 없습니다.");
	    }
	    
	    List<ConsultantContentResponseDto> responseList = new ArrayList<>();

	    for (ConsultantConsultingContent content : consulting.getConsultantConsultingContents()) {
	        ConsultantContentResponseDto responseDto = ConsultantContentResponseDto
	        		.builder()
	                .item(content.getItem())
	                .content(content.getContent())
	                .documentType(content.getDocumentType())
	                .build();
	        responseList.add(responseDto);
	    }

	    return responseList;
	}

	
	
	@Override
	@Transactional
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


	
	 @Override
	 @Transactional(readOnly = true)
	    public Page<ConsultantConsultingSummaryDto> getAllByCondition(Long consultantId, Pageable pageable) {
		 Page<ConsultantConsulting> entityPage =
				    consultantConsultingRepository.findWithAllConsultantConsultingByConsultantId(consultantId, pageable);


	        List<ConsultantConsultingSummaryDto> dtoList = new ArrayList<>();

	        for (ConsultantConsulting consultantConsulting : entityPage.getContent()) {
	            ConsultantConsultingSummaryDto consultantConsultingSummaryDto = toSummaryDto(consultantConsulting);
	            dtoList.add(consultantConsultingSummaryDto);
	        }


	        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements()); //전체 데이터 개수 유지해서 프론트에 알려줌
	    }

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
	    
	    @Override
	    @Transactional(readOnly = true)
	    public ConsultantEditDetailResponseDto getEditDetail(Long consultantId,Long consultantConsultingId) {
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
	    @Transactional(readOnly = true)
	    public ConsultantFeedBackDetailResponseDto getFeedbackDetail(Long consultantId, Long consultantConsultingId) {
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

	    
	    

	    
	}