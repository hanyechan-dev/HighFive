package com.jobPrize.admin01_service.service;

import com.jobPrize.admin01_service.dto.NotificationDto;
import com.jobPrize.entity.common.Comment;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.memToCom.Proposal;

public interface NotificationService {
	
	// 알림 생성 및 저장
	void createNotification();
	
	// 기업회원이 "합격" 버튼 클릭 시, 합격 및 해당 지원자 알림 발송
	NotificationDto passNotification();
	
	// 컨설턴트가 피드백 요청 또는 첨삭 요청 상세 모달에서 "승인" 버튼 클릭 시, 해당 요청자에게 알림 발송
	NotificationDto approvalNotification();
	
	// 컨설턴트가 피드백 요청 또는 첨삭 요청 상세 모달에서 "완료" 버튼 클릭 시, 해당 요청자에게 알림 발송
	NotificationDto completeNotification();
	
	// 채용제안 "수락" 버튼 클릭 시, 해당 채용제안 수락 알림 발송
	NotificationDto acceptNotification();
	
	// 채용제안 "거절" 버튼 클릭 시, 해당 채용제안 거절 알림 발송(이 기능이 과연 필요할까?)
	NotificationDto declineNotification();
	
	// 사용자가 작성한 게시글에 다른 사용자가 댓글 작성 시 알림 발송
	NotificationDto commentNotification();
	
}