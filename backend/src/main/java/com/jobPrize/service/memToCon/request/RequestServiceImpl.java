package com.jobPrize.service.memToCon.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.request.AiConsultingContentResponseDto;
import com.jobPrize.memberService.dto.request.AiConsultingResponseDto;
import com.jobPrize.memberService.dto.request.RequestCreateDto;
import com.jobPrize.memberService.dto.request.RequestDetailDto;
import com.jobPrize.memberService.dto.request.RequestResponseDto;
import com.jobPrize.memberService.dto.request.RequestSummaryDto;
import com.jobPrize.repository.memToCon.request.RequestRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.member.document.DocumentToJson;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

	private final MemberRepository memberRepository;
	
	private final RequestRepository requestRepository;
	
	private final DocumentToJson documentToJson;
	
	@Override
	public Page<RequestSummaryDto> getFeedbackRequestPage(Long id, Pageable pageable) {
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
	public Page<RequestSummaryDto> getEditRequestPage(Long id, Pageable pageable) {
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
	public RequestDetailDto getRequestDetail(Long id, Long requestId) {
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 컨설팅 요청입니다."));
		
		if(!request.getMember().getId().equals(id)) {
			throw new AccessDeniedException("요청한 회원만 조회할 수 있습니다.");
		}
		
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
	public void createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto) {
		if (!userType.equals(UserType.일반회원)) {
			throw new AccessDeniedException("일반회원만 요청할 수 있습니다.");
		}
		


		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

		Long careerDescriptionId = requestCreateDto.getCareerDescriptionId();
		Long coverLetterId = requestCreateDto.getCoverLetterId();

		String resumeJson =documentToJson.getResumeJsonByMemberId(id);
		String careerDescriptionJson =documentToJson.getCareerDescriptionJsonByCareerDescriptionId(id, careerDescriptionId);
		String coverLetterJson =documentToJson.getCoverLetterJsonByCoverLetterId(id, coverLetterId);

		Request request = Request.builder()
				.member(member)
				.targetJob(requestCreateDto.getTargetJob())
				.targetCompanyName(requestCreateDto.getTargetCompanyName())
				.resumeJson(resumeJson)
				.careerDescriptionJson(careerDescriptionJson)
				.coverLetterJson(coverLetterJson)
				.build();
		requestRepository.save(request);
		
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
