 package com.jobPrize.service.consultant.aiConsulting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;
import com.jobPrize.dto.member.aiConsulting.AiConsultingContentCreateDto;
import com.jobPrize.dto.member.aiConsulting.AiConsultingCreateDto;
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

	private final String ENTITY_NAME = "Ai 컨설팅";

	@Override
	@Transactional(readOnly = true)

	public Page<AiConsultingSummaryDto> readAiConsultingPage(UserType userType, ApprovalStatus approvalStatus, Pageable pageable) {

		String action = "조회";

		assertUtil.assertForConsultant(userType, approvalStatus, ENTITY_NAME, action);
		
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
	public AiConsultingDetailResponseDto readEditDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId) {
		
		String action = "조회";
		
		assertUtil.assertForConsultant(userType, approvalStatus, ENTITY_NAME, action);
		
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId) //상세 모달 정보
	            .orElseThrow(() -> new CustomEntityNotFoundException("Ai 컨설팅"));
	    
	    List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
	    List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos = new ArrayList<>();
	    
	    for(AiConsultingContent aiConsultingContent : aiConsultingContents) {
	    	AiConsultingContentResponseDto aiConsultingContentResponseDto = AiConsultingContentResponseDto.from(aiConsultingContent);
	    	aiConsultingContentResponseDtos.add(aiConsultingContentResponseDto);
	    }
	    
	    RequestResponseDto requestResponseDto = RequestResponseDto.from(aiConsulting.getRequest());
	    AiConsultingResponseDto aiConsultingResponseDto = AiConsultingResponseDto.of(aiConsulting, aiConsultingContentResponseDtos);
	    
	    AiConsultingDetailResponseDto aiConsultingDetailResponseDto = AiConsultingDetailResponseDto.of(requestResponseDto, aiConsultingResponseDto);
	    
	    return aiConsultingDetailResponseDto;

	}

	@Override
	@Transactional(readOnly = true)
	public AiConsultingDetailResponseDto readFeedbackDetail(UserType userType, ApprovalStatus approvalStatus, Long aiConsultingId) {
		
		String action = "조회";
		
		assertUtil.assertForConsultant(userType, approvalStatus, ENTITY_NAME, action);
		
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId)
	            .orElseThrow(() -> new CustomEntityNotFoundException("Ai 컨설팅"));

		List<AiConsultingContent> aiConsultingContents=aiConsulting.getAiConsultingContents();
		
		List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos = new ArrayList<>();
		
		for(AiConsultingContent aiConsultingContent : aiConsultingContents) {
			AiConsultingContentResponseDto aiConsultingContentResponseDto = AiConsultingContentResponseDto.from(aiConsultingContent);
			aiConsultingContentResponseDtos.add(aiConsultingContentResponseDto);
		}
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(aiConsulting.getRequest());
		AiConsultingResponseDto aiConsultingResponseDto = AiConsultingResponseDto.of(aiConsulting, aiConsultingContentResponseDtos);
		
		AiConsultingDetailResponseDto aiFeedbackDetailResponseDto =AiConsultingDetailResponseDto.of( requestResponseDto, aiConsultingResponseDto);
		
	    
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