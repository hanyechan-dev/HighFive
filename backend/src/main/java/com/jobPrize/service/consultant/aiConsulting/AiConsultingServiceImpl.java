 package com.jobPrize.service.consultant.aiConsulting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.consultant.aiConsultingContent.AiContentResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentCreateDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.repository.consultant.aiConsulting.AiConsultingRepository;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.service.consultant.aiConsultingContent.AiConsultingContentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AiConsultingServiceImpl implements AiConsultingService {

    private final AiConsultingRepository aiConsultingRepository;
    
    private final AiConsultingContentService aiConsultingContentService;
    
    private final RequestRepository requestRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<AiConsultingSummaryDto> readAiConsultingPageByCondition(Pageable pageable) {
	    Page<AiConsulting> entityPage = aiConsultingRepository.findAllByCondition(pageable);

	    List<AiConsultingSummaryDto> dtoList = new ArrayList<>();

	    for (AiConsulting aiConsulting : entityPage.getContent()) {
	        AiConsultingSummaryDto aiConsultingSummaryDto = toSummaryDto(aiConsulting);
	        dtoList.add(aiConsultingSummaryDto);
	    }

	    return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements()); //전체 데이터 개수 유지해서 프론트에 알려줌
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public AiEditDetailResponseDto readEditDetail(Long id, Long aiConsultingId) {
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 AiConsulting이 존재하지 않습니다."));
	    
	    if(!aiConsulting.getConsultantConsulting().getConsultant().equals(id)) {
	    	throw new AccessDeniedException("조회 권한이 없습니다.");
	    }
	    
	    return createDetailDto(aiConsulting, AiEditDetailResponseDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public AiFeedbackDetailResponseDto readFeedbackDetail(Long id, Long aiConsultingId) {
	    AiConsulting aiConsulting = aiConsultingRepository.findWithAllRequestByAiConsultingId(aiConsultingId)
	            .orElseThrow(() -> new IllegalArgumentException("해당 AiConsulting이 존재하지 않습니다."));
	    
	    if(!aiConsulting.getConsultantConsulting().getConsultant().equals(id)) {
	    	throw new AccessDeniedException("조회 권한이 없습니다.");
	    }
	    
	    return createDetailDto(aiConsulting, AiFeedbackDetailResponseDto.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	private AiConsultingSummaryDto toSummaryDto(AiConsulting aiConsulting) {
		return AiConsultingSummaryDto
				.builder()
				.aiConsultingId(aiConsulting.getId())
				.userName(aiConsulting.getRequest().getMember().getUser().getName())
				.targetJob(aiConsulting.getRequest().getTargetJob())
				.targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
				.requestedDate(aiConsulting.getRequest().getCreatedDate())
				.consultingType(aiConsulting.getType())
				.build();
	}

	// 공통 DTO 생성 메서드
	private <T> T createDetailDto(AiConsulting aiConsulting, Class<T> type) {
	    List<AiContentResponseDto> aiContents = new ArrayList<>();
	    for (AiConsultingContent aiConsultingContent : aiConsulting.getAiConsultingContents()) {
	        AiContentResponseDto aiCommentResponseDto = AiContentResponseDto
	        	.builder()
	            .item(aiConsultingContent.getItem())
	            .content(aiConsultingContent.getContent())
	            .build();
	        aiContents.add(aiCommentResponseDto);
	    }

	    if (type.equals(AiEditDetailResponseDto.class)) {  //첨삭 요청 상세 모달
	        return type.cast(AiEditDetailResponseDto
	        		.builder()
	                .targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
	                .targetJob(aiConsulting.getRequest().getTargetJob())
	                .requestedDate(aiConsulting.getRequest().getCreatedDate())
	                .resume(aiConsulting.getRequest().getResumeJson())
	                .careerDescription(aiConsulting.getRequest().getCareerDescriptionJson())
	                .coverLetter(aiConsulting.getRequest().getCoverLetterJson())
	                .aiContents(aiContents)
	                .build());
	    }

	    if (type.equals(AiFeedbackDetailResponseDto.class)) {  //피드백 요청 상세 모달
	        return type.cast(AiFeedbackDetailResponseDto
	        		.builder()
	                .targetCompanyName(aiConsulting.getRequest().getTargetCompanyName())
	                .targetJob(aiConsulting.getRequest().getTargetJob())
	                .requestedDate(aiConsulting.getRequest().getCreatedDate())
	                .resume(aiConsulting.getRequest().getResumeJson())
	                .careerDescription(aiConsulting.getRequest().getCareerDescriptionJson())
	                .coverLetter(aiConsulting.getRequest().getCoverLetterJson())
	                .aiContents(aiContents)
	                .build());
	    }

	    throw new IllegalArgumentException("지원하지 않는 DTO 타입입니다.");
	}


	@Override
	public void createAiConsulting(Long id, AiConsultingCreateDto aiConsultingCreateDto) {
		Request request = requestRepository.findById(aiConsultingCreateDto.getRequestId())
				.orElseThrow(()-> new EntityNotFoundException("존재하지 않는 요청입니다."));
		
		
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