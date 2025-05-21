package com.jobPrize.service.memToCon.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCon.request.RequestCreateDto;
import com.jobPrize.dto.memToCon.request.RequestDetailDto;
import com.jobPrize.dto.memToCon.request.RequestSummaryDto;
import com.jobPrize.entity.common.UserType;

public interface RequestService {
	Page<RequestSummaryDto> readFeedbackRequestPage(Long id, Pageable pageable);
	Page<RequestSummaryDto> readEditRequestPage(Long id, Pageable pageable);
	RequestDetailDto readRequestDetail(Long id, Long requestId);
	void createRequest(Long id, UserType userType, RequestCreateDto requestCreateDto);
}
