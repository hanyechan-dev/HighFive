package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.NotificationType;
import com.jobPrize.entity.common.User;

public interface NotificationService {
	
	// 알림 생성
	Notification createNotification(User sender, User receiver, NotificationType notificationType);
	
	// 알림 발송
	void sendNotification(User sender, User receiver, NotificationType notificationType);
	
	// 알림 조회
	List<Notification> readNotification(Long id);
}