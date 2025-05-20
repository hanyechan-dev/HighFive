package com.jobPrize.memberService.service.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.memberService.dto.request.RequestCreateDto;
import com.jobPrize.memberService.dto.request.RequestDetailDto;
import com.jobPrize.memberService.dto.request.RequestSummaryDto;

public interface RequestService {
	Page<RequestSummaryDto> getFeedbackRequestPage(Long id, Pageable pageable);
	Page<RequestSummaryDto> getEditRequestPage(Long id, Pageable pageable);
	RequestDetailDto getRequestDetail(Long id, Long requestId);
	void createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto);
}
