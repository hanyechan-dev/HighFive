package com.jobPrize.admin01_service.service;

import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.NotificationType;
import com.jobPrize.entity.common.User;

public interface NotificationService {
	
	// 알림 생성 및 저장
	Notification createNotification(User sender, User receiver, NotificationType notificationType);
	void sendNotification(User sender, User receiver, NotificationType notificationType);
}