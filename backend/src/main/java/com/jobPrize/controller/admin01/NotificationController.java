package com.jobPrize.controller.admin01;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.notification.NotificationDto;
import com.jobPrize.service.common.notification.NotificationService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notificaitons")
@RequiredArgsConstructor
public class NotificationController {
	
    private final NotificationService notificationService;
    
    // 알림 발송
    
    // 알림 조회
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotification(){
    	Long id = SecurityUtil.getId();
    	
        List<NotificationDto> notification = notificationService.readNotification(id);
        return ResponseEntity.status(HttpStatus.OK).body(notification);
    }
    
}