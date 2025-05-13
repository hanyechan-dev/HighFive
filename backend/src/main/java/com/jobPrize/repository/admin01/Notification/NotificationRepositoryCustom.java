package com.jobPrize.repository.admin01.Notification;

import java.util.List;

import com.jobPrize.entity.common.Notification;

public interface NotificationRepositoryCustom {
	List<Notification> findAllForOneMonth();	// 1개월 전까지의 모든 알림 조회
}
