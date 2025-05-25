package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.NotificationType;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.notification.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	
	private final NotificationRepository notificationRepository;
	
	// 알림 생성
	public Notification createNotification(User sender, User receiver, NotificationType notificationType) {
		Notification notification = Notification.builder()
				.NotificationType(notificationType)
				.message(notificationType.getMessageByType(sender, receiver))
				.sender(sender)
				.receiver(receiver)
				.build();
		
		notificationRepository.save(notification);
		return notification;
	}
	
	// 알림 발송
	public void sendNotification(User sender, User receiver, NotificationType notificationType) {
		Notification notification = createNotification(sender, receiver, notificationType);
		// WebSocket 통신 시 추후 구현
	}
	
	// 알림 조회
	public List<Notification> readNotification(Long id){
		List<Notification> notification = notificationRepository.findAllForOneMonthByUserId(id);
		return notification;
	}
}
