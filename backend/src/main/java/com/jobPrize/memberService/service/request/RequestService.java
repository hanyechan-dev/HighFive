package com.jobPrize.memberService.service.request;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.memberService.dto.request.RequestCreateDto;
import com.jobPrize.memberService.dto.request.RequestResponseDto;
import com.jobPrize.memberService.dto.request.RequestSummaryDto;

public interface RequestService {
	Page<RequestSummaryDto> getListFeedbackRequest(String token, Pageable pageable);
	Page<RequestSummaryDto> getListEditRequest(String token, Pageable pageable);
	Map<String, Object> getRequest(String token, Long requestId);
	void createRequest(String token, RequestCreateDto requestCreateDto);
}
