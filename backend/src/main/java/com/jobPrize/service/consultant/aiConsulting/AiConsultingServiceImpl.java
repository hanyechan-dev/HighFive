 package com.jobPrize.service.consultant.aiConsulting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentCreateDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.consultant.aiConsulting.AiConsultingRepository;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.service.consultant.aiConsultingContent.AiConsultingContentService;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AiConsultingServiceImpl implements AiConsultingService {

    private final AiConsultingRepository aiConsultingRepository;
    
    private final AiConsultingContentService aiConsultingContentService;
    
    private final RequestRepository requestRepository;

	private final AssertUtil assertUtil;

	@Override
	@Transactional(readOnly = true)

	public Page<AiConsultingSummaryDto> readAiConsultingPage(UserType userType, ApprovalStatus approvalStatus, Pageable pageable) {

		assertUtil.assertForConsultant(userType, approvalStatus, "조회");
		
	    Page<AiConsulting> AiConsultingPage = aiConsultingRepository.findAllByCondition(pageable); //컨설팅 요청 페이지

	    List<AiConsultingSummaryDto> aiConsultingSummaryDtos = new ArrayList<>();

	    for (AiConsulting aiConsulting : AiConsultingPage.getContent()) {
	        AiConsultingSummaryDto aiConsultingSummaryDto = AiConsultingSummaryDto.from(aiConsulting);
	        aiConsultingSummaryDtos.add(aiConsultingSummaryDto);
	    }

	    return new PageImpl<>(aiConsultingSummaryDtos, pageable, AiConsultingPage.getTotalElements()); //전체 데이터 개수 유지해서 프론트에 알려줌
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public AiEditDetailResponseDto readEditDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId) {
		
		assertUtil.assertForConsultant(userType, approvalStatus, "조회");
		
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId) //상세 모달 정보
	            .orElseThrow(() -> new CustomEntityNotFoundException("Ai 컨설팅"));
	    
	    List<AiConsultingContent> aiConsultingContents=aiConsulting.getAiConsultingContents();
	    
	    List<AiContentResponseDto> aiContentResponseDtos = new ArrayList<>();
	    
	    for(AiConsultingContent aiConsultingContent : aiConsultingContents) {
	    	AiContentResponseDto aiContentResponseDto = AiContentResponseDto.from(aiConsultingContent);
	    	aiContentResponseDtos.add(aiContentResponseDto);
	    }
	    
	    AiEditDetailResponseDto aiEditDetailResponseDto = AiEditDetailResponseDto.of(aiConsulting, aiContentResponseDtos);
	    
	    return aiEditDetailResponseDto;

	}

	@Override
	@Transactional(readOnly = true)
	public AiFeedbackDetailResponseDto readFeedbackDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId) {
		
		assertUtil.assertForConsultant(userType, approvalStatus, "조회");
		
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId)
	            .orElseThrow(() -> new CustomEntityNotFoundException("Ai 컨설팅"));

		List<AiConsultingContent> aiConsultingContents=aiConsulting.getAiConsultingContents();
		
		List<AiContentResponseDto> aiContentResponseDtos = new ArrayList<>();
		
		for(AiConsultingContent aiConsultingContent : aiConsultingContents) {
			AiContentResponseDto aiContentResponseDto = AiContentResponseDto.from(aiConsultingContent);
			aiContentResponseDtos.add(aiContentResponseDto);
		}
		
		AiFeedbackDetailResponseDto aiFeedbackDetailResponseDto =AiFeedbackDetailResponseDto.of(aiConsulting, aiContentResponseDtos);
		
	    
	    return aiFeedbackDetailResponseDto;
	}
	


	@Override
	public void createAiConsulting(AiConsultingCreateDto aiConsultingCreateDto, Long requestId ) {
		Request request = requestRepository.findById(requestId)
				.orElseThrow(() -> new CustomEntityNotFoundException("요청"));
		
		
		AiConsulting aiConsulting = AiConsulting.builder()
				.request(request)
				.type(aiConsultingCreateDto.getType())
				.build();
		
		aiConsultingRepository.save(aiConsulting);
		
		List<AiConsultingContentCreateDto> aiConsultingContentCreateDtos = aiConsultingCreateDto.getAiConsultingContentCreateDtos();
		for(AiConsultingContentCreateDto aiConsultingContentCreateDto : aiConsultingContentCreateDtos) {
			aiConsultingContentService.createAiConsultingContent(aiConsulting, aiConsultingContentCreateDto);
		}
		
		
	}


}