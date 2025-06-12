package com.jobPrize.service.memToCon.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ConsultingType;
import com.jobPrize.enumerate.RequestStatus;
import com.jobPrize.enumerate.UserType;
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

	private final static String ENTITY_NAME = "컨설팅 요청";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;

	@Override
	public void createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto) {

		String action = "수행";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
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
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id, ConsultingType.피드백 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			Map<String,Object> result = getPriorityDateAndRequestStatus(request);
			LocalDate date = (LocalDate) result.get("date");
			
			RequestStatus requestStatus = (RequestStatus)result.get("requestStatus");
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date,requestStatus);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RequestSummaryDto> readEditRequestPage(Long id, Pageable pageable) {
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,ConsultingType.첨삭 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			Map<String,Object> result = getPriorityDateAndRequestStatus(request);
			LocalDate date = (LocalDate) result.get("date");
			
			RequestStatus requestStatus = (RequestStatus)result.get("requestStatus");
			
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date,requestStatus);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public RequestDetailDto readRequestDetail(Long id, UserType userType, Long requestId) {

		String action = "조회";
		
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = requestRepository.findMemberIdByRequestId(requestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));
		
		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
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
	
	@Override
	public void createRequestToConsultant(Long id, boolean isSubscribed, Long requestId) {

		String action = "수행";
		
		assertUtil.assertSubscription(ALLOWED_USER_TYPE, isSubscribed, ENTITY_NAME, action);
		
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		request.getAiConsulting().RequestToConsultant();
		
	}
	
	private void postRequestToPython(Request request) {
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		
		AiConsultingCreateDto aiConsultingCreateDto = webClientUtil.postRequestToPython(requestResponseDto);
    	
		aiConsultingService.createAiConsulting(aiConsultingCreateDto, requestResponseDto.getId());
		
	}

	
	private Map<String,Object> getPriorityDateAndRequestStatus(Request request) {
		LocalDate date = null;
		RequestStatus requestStatus;
		
		if(request.getAiConsulting()!= null) {
			if(request.getAiConsulting().getConsultantConsulting()!=null) {
				if(request.getAiConsulting().getConsultantConsulting().getCompletedDate()!=null) {
					date = request.getAiConsulting().getConsultantConsulting().getCompletedDate();
					requestStatus=RequestStatus.완료;
				}
				else {
					date = request.getAiConsulting().getConsultantConsulting().getCreatedDate();
					requestStatus=RequestStatus.승인;
				}
			}
			else if(request.getAiConsulting().getRequestedDate()!=null) {
				date = request.getAiConsulting().getRequestedDate();
				requestStatus=RequestStatus.요청;
			}
			
			else{
				date = request.getCreatedDate();
				requestStatus=RequestStatus.AI;
			}
		}
		else{
			date = request.getCreatedDate();
			requestStatus=RequestStatus.AI;
		}
		
		Map<String,Object> result = new HashMap<>();
		
		result.put("date", date);
		result.put("requestStatus", requestStatus);
		
		
		return result;
	}




	

}
