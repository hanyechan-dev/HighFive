package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.NotificationDto;
import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.NotificationType;

public interface NotificationService {
	
	// 알림 생성
	Notification createNotification(Long id, Long receiverId, NotificationType notificationType);
	
	// 알림 발송
	void sendNotification(Long id, Long receiverId, NotificationType notificationType);
	
	// 알림 조회
	List<NotificationDto> readNotification(Long id);
}