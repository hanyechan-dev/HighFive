package com.jobPrize.controller.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.notification.CreateDto;
import com.jobPrize.dto.common.notification.NotificationDto;
import com.jobPrize.dto.common.notification.SendDto;
import com.jobPrize.enumerate.NotificationType;
import com.jobPrize.service.common.notification.NotificationService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
	
    private final NotificationService notificationService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    
    // 알림 생성
    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody CreateDto createDto){
    	Long id = SecurityUtil.getId();
    	Long receiverId = createDto.getReceiverId();
    	NotificationType notificationType = createDto.getNotificationType();
    	
    	NotificationDto notificationDto = notificationService.createNotification(id, receiverId, notificationType);
    	return ResponseEntity.status(HttpStatus.OK).body(notificationDto);
    }
    
    // 알림 조회
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotification(){
    	Long id = SecurityUtil.getId();
    	
        List<NotificationDto> notification = notificationService.readNotification(id);
        return ResponseEntity.status(HttpStatus.OK).body(notification);
    }
    
    // 알림 발송
    @MessageMapping("/notification/send")
	public void sendNotification(@RequestBody SendDto sendDto) {
    	Long id = sendDto.getId();
    	Long receiverId = sendDto.getReceiverId();
    	NotificationType notificationType = sendDto.getNotificationType();
    	
		String destination = "/topic/notification";
		
		NotificationDto notificationDto = notificationService.createNotification(id, receiverId, notificationType);
		System.out.println("알림 발송 시도 -> 목적지: " + destination + ", 내용: " + notificationDto.getMessage());
		simpMessagingTemplate.convertAndSend(destination, notificationDto);
	}	
}