package com.jobPrize.consultantController.aiConsulting;


import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.consultant.aiConuslting.AiConsultingSummaryDto;
import com.jobPrize.dto.consultant.aiConuslting.AiEditDetailResponseDto;
import com.jobPrize.dto.consultant.aiConuslting.AiFeedbackDetailResponseDto;
import com.jobPrize.dto.memToCon.aiConsulting.AiConsultingCreateDto;
import com.jobPrize.service.consultant.aiConsulting.AiConsultingService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultant/ai-consulting") 
@RequiredArgsConstructor
public class AiConsultingController {

    private final AiConsultingService aiConsultingService;

   
    @PostMapping("/create")
    public ResponseEntity<String> createAiConsulting
    (@RequestBody AiConsultingCreateDto aiConsultingCreateDto){
    	
    	Long id = SecurityUtil.getId();
        
        aiConsultingService.createAiConsulting(id, aiConsultingCreateDto);
        return ResponseEntity.ok("AI 컨설팅 요청이 완료되었습니다.");
    }

   
    @GetMapping("/list")
    public ResponseEntity<Page<AiConsultingSummaryDto>> readAiConsultingPage(Pageable pageable) {
    	Long id = SecurityUtil.getId();
        Page<AiConsultingSummaryDto> page = aiConsultingService.readAiConsultingPage(pageable);
        return ResponseEntity.ok(page);
    }

 
    @PostMapping("/edit-detail")
    public ResponseEntity<AiEditDetailResponseDto> readEditDetail
    (@RequestBody Long aiConsultingId) {
    	Long id = SecurityUtil.getId();
    	
        AiEditDetailResponseDto aiEditDetailResponseDto = aiConsultingService.readEditDetail(id, aiConsultingId);
        return ResponseEntity.ok(aiEditDetailResponseDto);
    }

 
    @PostMapping("/feedback-detail")
    public ResponseEntity<AiFeedbackDetailResponseDto> readFeedbackDetail
    (@RequestBody Long aiConsultingId) {
    	Long id = SecurityUtil.getId();
    	
        AiFeedbackDetailResponseDto aiFeedbackDetailResponseDto = aiConsultingService.readFeedbackDetail(id, aiConsultingId);
        return ResponseEntity.ok(aiFeedbackDetailResponseDto);
    }
    
    
    
    
    
    
}
