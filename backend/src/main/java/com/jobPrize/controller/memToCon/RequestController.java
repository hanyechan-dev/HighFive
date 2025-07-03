package com.jobPrize.controller.memToCon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.member.request.CompletedRequestDetailDto;
import com.jobPrize.dto.member.request.RequestCreateDto;
import com.jobPrize.dto.member.request.RequestDetailDto;
import com.jobPrize.dto.member.request.RequestSummaryDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.memToCon.request.RequestService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/feedbacks")
    public ResponseEntity<Page<RequestSummaryDto>> readMyFeedbackRequests(Pageable pageable) {
    	
    	Long id = SecurityUtil.getId();
    	
    	Page<RequestSummaryDto> requestSummaryDtos = requestService.readFeedbackRequestPage(id, pageable);
    	
        return ResponseEntity.status(HttpStatus.OK).body(requestSummaryDtos);
    }

    @GetMapping("/edits")
    public ResponseEntity<Page<RequestSummaryDto>> readMyEditRequests(Pageable pageable) {
    	
    	Long id = SecurityUtil.getId();
    	
    	Page<RequestSummaryDto> requestSummaryDtos = requestService.readEditRequestPage(id, pageable);
    	
        return ResponseEntity.status(HttpStatus.OK).body(requestSummaryDtos);
    }
    
    @PostMapping
    public ResponseEntity<RequestDetailDto> createRequest(@RequestBody @Valid RequestCreateDto requestCreateDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	Long requestId = requestService.createRequest(id, userType, requestCreateDto);
    	
    	RequestDetailDto requestDetailDto = requestService.readRequestDetail(id, userType, requestId);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(requestDetailDto);
    	
    }
    
    @PostMapping("/detail")
    public ResponseEntity<RequestDetailDto> readRequest(@RequestBody @Valid IdDto idDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	RequestDetailDto requestDetailDto = requestService.readRequestDetail(id, userType, idDto.getId());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(requestDetailDto);
    	
    }
    
    @PostMapping("/completions/detail")
    public ResponseEntity<CompletedRequestDetailDto> readCompletedRequest(@RequestBody @Valid IdDto idDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	CompletedRequestDetailDto completedRequestDetailDto = requestService.readCompletedRequestDetail(id, userType, idDto.getId());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(completedRequestDetailDto);
    	
    }
    
    @PutMapping
    public ResponseEntity<Void> requestToConsultant(@RequestBody @Valid IdDto idDto){
    	
    	Long id = SecurityUtil.getId();
    	
    	boolean isSubscribed = SecurityUtil.isSubscribed();
    	
    	requestService.createRequestToConsultant(id, isSubscribed, idDto.getId());
    	
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
