package com.jobPrize.admin01_service.service;

import org.springframework.stereotype.Service;

import com.jobPrize.admin01_service.dto.NotificationDto;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.repository.common.notification.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	
	private final NotificationRepository notificationRepository;
	
	// 알림 생성 및 저장
	private void createNotification(User sender, User receiver, NotificationType notificationType) {
		String message;
		if(notificationType == NotificationType.채용제안) {
			message = sender.getCompany().getCompanyName()+"社로 부터"+ receiver.getName()+"채용 제안이 도착하였습니다.";
		}
		
		
		Notification notification = Notification.builder()
				.sender(sender)
				.receiver(receiver)
				.message(message)
				.notificationType(notificationType)
				.build();
		
		notificationRepository.save(notification);
	}
	
	// 컨설턴트가 피드백 요청 또는 첨삭 요청 상세 모달에서 "승인" 버튼 클릭 시, 해당 요청자에게 알림 발송
	NotificationDto approvalNotification() {
		
	}
	
	// 컨설턴트가 피드백 요청 또는 첨삭 요청 상세 모달에서 "완료" 버튼 클릭 시, 해당 요청자에게 알림 발송
	NotificationDto completeNotification() {
		
	}
	
	// 채용제안 "수락" 버튼 클릭 시, 해당 채용제안 수락 알림 발송
	NotificationDto acceptNotification() {
		
	}
	
	// 채용제안 "거절" 버튼 클릭 시, 해당 채용제안 거절 알림 발송(이 기능이 과연 필요할까?)
	NotificationDto declineNotification() {
		
	}
	
	// 사용자가 작성한 게시글에 다른 사용자가 댓글 작성 시 알림 발송
	NotificationDto commentNotification() {
		
	}
	
	// 기업회원이 채용제안 기능 사용 시 알림 발송(기능정읫

}
