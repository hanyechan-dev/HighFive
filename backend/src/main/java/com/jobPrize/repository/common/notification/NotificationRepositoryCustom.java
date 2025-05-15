package com.jobPrize.repository.common.notification;

import java.util.List;

import com.jobPrize.entity.common.Notification;

public interface NotificationRepositoryCustom {
	List<Notification> findAllForOneMonthByUserId(Long id);	// 1개월 전까지의 모든 알림 조회
}
