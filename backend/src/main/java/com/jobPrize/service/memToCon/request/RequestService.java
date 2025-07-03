package com.jobPrize.service.memToCon.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.member.request.CompletedRequestDetailDto;
import com.jobPrize.dto.member.request.RequestCreateDto;
import com.jobPrize.dto.member.request.RequestDetailDto;
import com.jobPrize.dto.member.request.RequestSummaryDto;
import com.jobPrize.enumerate.UserType;

public interface RequestService {
	Long createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto);
	Page<RequestSummaryDto> readFeedbackRequestPage(Long id, Pageable pageable);
	Page<RequestSummaryDto> readEditRequestPage(Long id, Pageable pageable);
	RequestDetailDto readRequestDetail(Long id, UserType userType, Long requestId);
	CompletedRequestDetailDto readCompletedRequestDetail(Long id, UserType userType, Long requestId);
	void createRequestToConsultant(Long id, boolean isSubscribed, Long requestId);
	
}
