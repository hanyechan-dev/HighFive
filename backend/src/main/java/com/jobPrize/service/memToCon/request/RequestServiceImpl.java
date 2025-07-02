package com.jobPrize.service.memToCon.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingContentResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingResponseDto;
import com.jobPrize.dto.memToCon.consultantConsulting.ConsultantConsultingContentResponseDto;
import com.jobPrize.dto.memToCon.consultantConsulting.ConsultantConsultingForMemberResponseDto;
import com.jobPrize.dto.memToCon.request.RequestResponseDto;
import com.jobPrize.dto.member.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.dto.member.request.CompletedRequestDetailDto;
import com.jobPrize.dto.member.request.RequestCreateDto;
import com.jobPrize.dto.member.request.RequestDetailDto;
import com.jobPrize.dto.member.request.RequestSummaryDto;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.ConsultantConsultingContent;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ConsultingType;
import com.jobPrize.enumerate.RequestStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.admin.editPrompt.EditPromptService;
import com.jobPrize.service.admin.feedbackPrompt.FeedbackPromptService;
import com.jobPrize.service.consultant.aiConsulting.AiConsultingService;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.JsonUtil;
import com.jobPrize.util.PromptBuilder;
import com.jobPrize.util.WebClientUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	private final MemberRepository memberRepository;
	
	private final RequestRepository requestRepository;
	
	private final AiConsultingService aiConsultingService;
	
	private final EditPromptService editPromptService;
	
	private final FeedbackPromptService feedbackPromptService;
	
	private final JsonUtil jsonUtil;

	private final AssertUtil assertUtil;
	
	private final WebClientUtil webClientUtil;
	
	private final PromptBuilder promptBuilder;

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

		Request request = Request.of(member, requestCreateDto, RequestStatus.AI, resumeJson, careerDescriptionJson, coverLetterJson);

		requestRepository.save(request);
		
		postRequestToPython(request);

		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RequestSummaryDto> readFeedbackRequestPage(Long id, Pageable pageable) {
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id, ConsultingType.피드백 ,pageable);
		
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
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,ConsultingType.첨삭 ,pageable);
		
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

		return readUncompletedRequestDetail(id, userType, requestId);
	}
	
	
	@Override
	public CompletedRequestDetailDto readCompletedRequestDetail(Long id, UserType userType, Long requestId) {

		
		Request request = requestRepository.findWithConsultantConsultingByRequestId(requestId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		ConsultantConsulting consultantConsulting = request.getAiConsulting().getConsultantConsulting();
		
		List<ConsultantConsultingContent> consultantConsultingContents = consultantConsulting.getConsultantConsultingContents();
		List<ConsultantConsultingContentResponseDto> consultantConsultingContentResponseDtos = new ArrayList<>();
		for(ConsultantConsultingContent consultantConsultingContent : consultantConsultingContents) {
			ConsultantConsultingContentResponseDto consultantConsultingContentResponseDto = ConsultantConsultingContentResponseDto.from(consultantConsultingContent);
			consultantConsultingContentResponseDtos.add(consultantConsultingContentResponseDto);
		}
		
		RequestDetailDto requestDetailDto=readUncompletedRequestDetail(id, userType, requestId);
		
		RequestResponseDto requestResponseDto = requestDetailDto.getRequestResponseDto();
		AiConsultingResponseDto aiConsultingResponseDto = requestDetailDto.getAiConsultingResponseDto();
		ConsultantConsultingForMemberResponseDto consultantConsultingForMemberResponseDto = ConsultantConsultingForMemberResponseDto.of(consultantConsulting, consultantConsultingContentResponseDtos);
		
		CompletedRequestDetailDto completedRequestDetailDto = CompletedRequestDetailDto.of(requestResponseDto, aiConsultingResponseDto, consultantConsultingForMemberResponseDto);
		
		return completedRequestDetailDto;
	}
	
	
	
	@Override
	public void createRequestToConsultant(Long id, boolean isSubscribed, Long requestId) {

		String action = "수행";
		
		assertUtil.assertSubscription(ALLOWED_USER_TYPE, isSubscribed, ENTITY_NAME, action);
		
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		request.updateRequestStatus(RequestStatus.요청);
		
		request.getAiConsulting().RequestToConsultant();
		
	}
	
	private void postRequestToPython(Request request) {
		
		ConsultingType consultingType = request.getConsultingType();
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		
		List<Map<String, String>> promptList;
		
		if(ConsultingType.첨삭.equals(consultingType)) {
			System.out.println("첨삭 요청 들어옴");
			EditPromptResponseDto editPromptResponseDto = editPromptService.readAppliedEditPrompt(UserType.관리자);
			String guidePrompt = editPromptResponseDto.getContent();
			promptList = promptBuilder.getEditPrompt(requestResponseDto, guidePrompt);
		}
		
		else{
			System.out.println("피드백 요청 들어옴");
			FeedbackPromptResponseDto feedbackPromptResponseDto = feedbackPromptService.readAppliedFeedbackPrompt(UserType.관리자);
			String guidePrompt = feedbackPromptResponseDto.getContent();
			promptList = promptBuilder.getFeedbackPrompt(requestResponseDto, guidePrompt);
		}
		
		
		AiConsultingCreateDto aiConsultingCreateDto = webClientUtil.postRequestToPython(promptList, consultingType);
    	
		aiConsultingService.createAiConsulting(aiConsultingCreateDto, requestResponseDto.getId());
		
	}

	
	private LocalDate getPriorityDate(Request request) {
		
		RequestStatus requestStatus = request.getRequestStatus();
		
		LocalDate date = null;
		
		if(RequestStatus.AI.equals(requestStatus)) {
			date=request.getCreatedDate();
		}
		else if(RequestStatus.요청.equals(requestStatus)) {
			date=request.getAiConsulting().getRequestedDate();
		}
		else if(RequestStatus.승인.equals(requestStatus)) {
			date=request.getAiConsulting().getConsultantConsulting().getCreatedDate();
		}
		else {
			date=request.getAiConsulting().getConsultantConsulting().getCreatedDate();
		}

		
		return date;
	}
	
	private RequestDetailDto readUncompletedRequestDetail(Long id, UserType userType, Long requestId) {

		String action = "조회";
		
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = requestRepository.findMemberIdByRequestId(requestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));
		
		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		AiConsulting aiConsulting = request.getAiConsulting();
		
		List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
		List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos = new ArrayList<>();
		
		for(AiConsultingContent aiConsultingContent : aiConsultingContents ) {
			AiConsultingContentResponseDto aiConsultingContentResponseDto = AiConsultingContentResponseDto.from(aiConsultingContent);
			aiConsultingContentResponseDtos.add(aiConsultingContentResponseDto);
		}
		
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		
		AiConsultingResponseDto aiConsultingResponseDto = AiConsultingResponseDto.of(aiConsulting, aiConsultingContentResponseDtos);
		
	
		return RequestDetailDto.of(requestResponseDto, aiConsultingResponseDto);
	}






	

}
