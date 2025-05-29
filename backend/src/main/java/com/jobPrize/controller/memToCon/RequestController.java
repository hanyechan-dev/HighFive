package com.jobPrize.controller.memToCon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.memToCon.request.RequestCreateDto;
import com.jobPrize.dto.memToCon.request.RequestDetailDto;
import com.jobPrize.dto.memToCon.request.RequestSummaryDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.memToCon.request.RequestService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/requests")
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
    public ResponseEntity<Void> createRequest(@RequestBody @Valid RequestCreateDto requestCreateDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	requestService.createRequest(id, userType, requestCreateDto);
    	
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	
    }
    
    @PostMapping("/detail")
    public ResponseEntity<RequestDetailDto> readRequest(@RequestBody @Valid IdDto idDto) {
    	
    	Long id = SecurityUtil.getId();
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	RequestDetailDto requestDetailDto = requestService.readRequestDetail(id, userType, idDto.getId());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(requestDetailDto);
    	
    }

}
