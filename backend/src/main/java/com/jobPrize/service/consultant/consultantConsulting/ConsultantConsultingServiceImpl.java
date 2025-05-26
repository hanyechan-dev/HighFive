package com.jobPrize.service.consultant.consultantConsulting;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingSummaryDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantConsultingUpdateDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantEditDetailResponseDto;
import com.jobPrize.dto.consultant.consultantConsulting.ConsultantFeedBackDetailResponseDto;
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
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantConsultingServiceImpl implements ConsultantConsultingService {
	
	private final ConsultantConsultingRepository consultantConsultingRepository;
	private final AiConsultingRepository aiConsultingRepository;
	private final ConsultantRepository consultantRepository;
	private final ConsultantConsultingContentService consultantConsultingContentService;
	private final AssertUtil assertUtil;
	
	@Override
	public void approveConsulting(Long id, UserType userType, Long aiConsultingId) {

		assertUtil.assertUserType(userType, UserType.컨설턴트회원, "승인");

	    AiConsulting aiConsulting = aiConsultingRepository.findById(aiConsultingId)
	        .orElseThrow(() -> new CustomEntityNotFoundException("Ai 컨설팅"));

	    Consultant consultant = consultantRepository.findById(id)
	        .orElseThrow(() -> new CustomEntityNotFoundException("컨설턴트"));


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


	public void updateConsultantConsulting(Long id, ConsultantConsultingUpdateDto consultantConsultingUpdateDto) {

		ConsultantConsulting consultantConsulting = consultantConsultingRepository.findById(consultantConsultingUpdateDto.getConsultantConsultingid())
				.orElseThrow(()-> new CustomEntityNotFoundException("컨설턴트 컨설팅"));
		
		assertUtil.assertId(id, consultantConsulting, "수정");
		
		List<ConsultantContentUpdateDto> consultantContentUpdateDtos = consultantConsultingUpdateDto.getConsultantContentUpdateDtos();
		
		for(ConsultantContentUpdateDto consultantContentUpdateDto : consultantContentUpdateDtos) {
			consultantConsultingContentService.updateConsultantConsultingContent(consultantContentUpdateDto);
		}
		
		
	}
	 
	
	@Override
	public void completeConsulting(Long id, Long consultantConsultingId) {
	    ConsultantConsulting consultantConsulting = consultantConsultingRepository.findById(consultantConsultingId)
	        .orElseThrow(() -> new CustomEntityNotFoundException("컨설턴트 컨설팅"));
	    
	    assertUtil.assertId(id, consultantConsulting, "완료");

	    if (consultantConsulting.getCompletedDate() != null) {
	        throw new IllegalStateException("이미 완료된 컨설팅입니다.");
	    }

	    consultantConsulting.complete();
	    consultantConsultingRepository.save(consultantConsulting);
	}


	// 컨설팅 현황 관리 페이지 
	 @Override
	 public Page<ConsultantConsultingSummaryDto> readConsultantConsultingPage(Long id, Pageable pageable) {

		 Page<ConsultantConsulting> entityPage =
				    consultantConsultingRepository.findWithAllConsultantConsultingByConsultantId(id, pageable);


	        List<ConsultantConsultingSummaryDto> dtoList = new ArrayList<>();

	        for (ConsultantConsulting consultantConsulting : entityPage.getContent()) {
	            ConsultantConsultingSummaryDto consultantConsultingSummaryDto = ConsultantConsultingSummaryDto.from(consultantConsulting);
	            dtoList.add(consultantConsultingSummaryDto);
	        }


	        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements()); //전체 데이터 개수 유지해서 프론트에 알려줌
	    }


	    
	    @Override
	    public ConsultantEditDetailResponseDto readEditDetail(Long id, UserType userType, Long consultantConsultingId) {
	        ConsultantConsulting consultantConsulting = consultantConsultingRepository.findWithConsultantConsultingContentsByConsultantConsultingId(consultantConsultingId)
	            .orElseThrow(() -> new CustomEntityNotFoundException("컨설턴트 컨설팅"));
	        
	        if(UserType.컨설턴트회원.equals(userType)) {
	        	if(!consultantConsulting.getConsultant().getId().equals(id)) {
	        		throw new CustomOwnerMismatchException("ConsultantConsulting", "조회");
	        	}
	        }
	        else if(UserType.일반회원.equals(userType)) {
	        	if(!consultantConsulting.getAiConsulting().getRequest().getMember().getId().equals(id)) {
	        		throw new CustomOwnerMismatchException("ConsultantConsulting", "조회");
	        	}
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
	        
	        ConsultantEditDetailResponseDto consultantEditDetailResponseDto = ConsultantEditDetailResponseDto.of(consultantConsulting, aiContents, consultantContents);

	        return consultantEditDetailResponseDto;
	    }

	    
	    @Override
	    public ConsultantFeedBackDetailResponseDto readFeedbackDetail(Long id, UserType userType, Long consultantConsultingId) {
	        ConsultantConsulting consultantConsulting = consultantConsultingRepository
	            .findWithConsultantConsultingContentsByConsultantConsultingId(consultantConsultingId)
	            .orElseThrow(() -> new CustomEntityNotFoundException("컨설턴트 컨설팅"));
	        
	        if(UserType.컨설턴트회원.equals(userType)) {
	        	if(!consultantConsulting.getConsultant().getId().equals(id)) {
	        		throw new CustomOwnerMismatchException("ConsultantConsulting", "조회");
	        	}
	        }
	        else if(UserType.일반회원.equals(userType)) {
	        	if(!consultantConsulting.getAiConsulting().getRequest().getMember().getId().equals(id)) {
	        		throw new CustomOwnerMismatchException("ConsultantConsulting", "조회");
	        	}
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
	        
	        ConsultantFeedBackDetailResponseDto consultantFeedBackDetailResponseDto = ConsultantFeedBackDetailResponseDto.of(consultantConsulting, aiContents, consultantContents);

	        return consultantFeedBackDetailResponseDto;
	    }

}