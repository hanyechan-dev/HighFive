package com.jobPrize.service.memToCon.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.request.RequestCreateDto;
import com.jobPrize.dto.memToCon.request.RequestDetailDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;
import com.jobPrize.dto.memToCon.request.RequestSummaryDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.consultant.aiConsulting.AiConsultingService;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.JsonUtil;
import com.jobPrize.util.WebClientUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	private final MemberRepository memberRepository;
	
	private final RequestRepository requestRepository;
	
	private final AiConsultingService aiConsultingService;
	
	private final JsonUtil jsonUtil;

	private final AssertUtil assertUtil;
	
	private final WebClientUtil webClientUtil;

	@Override
	public void createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto) {

		assertUtil.assertUserType(userType, UserType.일반회원, "컨설팅 요청");
		
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		Long careerDescriptionId = requestCreateDto.getCareerDescriptionId();
		Long coverLetterId = requestCreateDto.getCoverLetterId();

		String resumeJson =jsonUtil.getResumeJsonByMemberId(id);
		String careerDescriptionJson =jsonUtil.getCareerDescriptionJsonByCareerDescriptionId(id, careerDescriptionId);
		String coverLetterJson =jsonUtil.getCoverLetterJsonByCoverLetterId(id, coverLetterId);

		Request request = Request.of(member, requestCreateDto, resumeJson, careerDescriptionJson, coverLetterJson);

		requestRepository.save(request);
		
		postRequestToPython(request);

		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RequestSummaryDto> readFeedbackRequestPage(Long id, Pageable pageable) {
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,CommonEnum.ConsultingType.피드백 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			LocalDate date = getPriorityDate(request);
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RequestSummaryDto> readEditRequestPage(Long id, Pageable pageable) {
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,CommonEnum.ConsultingType.첨삭 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			LocalDate date = getPriorityDate(request);
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public RequestDetailDto readRequestDetail(Long id, UserType userType, Long requestId) {
		
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("컨설팅 요청"));
		
		assertUtil.assertId(id, request, "조회");
		
		AiConsulting aiConsulting = request.getAiConsulting();
		
		List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		
		AiConsultingResponseDto aiConsultingResponseDto = AiConsultingResponseDto.from(aiConsulting);
		
		List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos = new ArrayList<>();
		
		for(AiConsultingContent aiConsultingContent : aiConsultingContents ) {
			AiConsultingContentResponseDto aiConsultingContentResponseDto = AiConsultingContentResponseDto.from(aiConsultingContent);
			aiConsultingContentResponseDtos.add(aiConsultingContentResponseDto);
		}
		
	
		return RequestDetailDto.of(requestResponseDto, aiConsultingResponseDto, aiConsultingContentResponseDtos);
	}
	
	private void postRequestToPython(Request request) {
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		
		AiConsultingCreateDto aiConsultingCreateDto = webClientUtil.postRequestToPython(requestResponseDto);
    	
		aiConsultingService.createAiConsulting(aiConsultingCreateDto, requestResponseDto.getId());
		
	}
	
	private LocalDate getPriorityDate(Request request) {
		LocalDate date = null;
		
		if(request.getAiConsulting()!= null) {
			if(request.getAiConsulting().getConsultantConsulting()!=null) {
				if(request.getAiConsulting().getConsultantConsulting().getCompletedDate()!=null) {
					date = request.getAiConsulting().getConsultantConsulting().getCompletedDate();
				}
				else {
					date = request.getAiConsulting().getConsultantConsulting().getCreatedDate();
				}
			}
			else if(request.getAiConsulting().getRequestedDate()!=null) {
				date = request.getAiConsulting().getRequestedDate();
			}
			
			else{
				date = request.getCreatedDate();
			}
		}
		else{
			date = request.getCreatedDate();
		}
		
		return date;
	}


	

}
