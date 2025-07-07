package com.jobPrize.service.common.notification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.notification.NotificationDto;
import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.NotificationType;
import com.jobPrize.repository.common.notification.NotificationRepository;
import com.jobPrize.repository.common.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	
	private final NotificationRepository notificationRepository;
	
	private final UserRepository userRepository;
	
	// 알림 생성
	public NotificationDto createNotification(Long id, Long receiverId, NotificationType notificationType) {
		
		User sender = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		
		User receiver = userRepository.findByIdAndDeletedDateIsNull(receiverId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		
		Notification tempNotification = Notification.builder()
				.NotificationType(notificationType)
				.message(notificationType.getMessageByType(sender, receiver))
				.sender(sender)
				.receiver(receiver)
				.build();
		
		Notification notification = notificationRepository.save(tempNotification);
		
		NotificationDto dto = NotificationDto.builder()
				.notificationId(notification.getId())
				.id(receiverId)
				.message(notification.getMessage())
				.createdTime(notification.getCreatedTime())
				.build();
				
		return dto;
	}
	
	// 알림 조회
	public List<NotificationDto> readNotification(Long id){
		List<Notification> notification = notificationRepository.findAllForOneMonthByUserId(id);
		
		return notification.stream()
				.map(noti -> NotificationDto.builder()
						.notificationId(noti.getId())
						.id(id)
						.message(noti.getMessage())
						.createdTime(noti.getCreatedTime())
						.build()
						)
				.collect(Collectors.toList());
	}
}
