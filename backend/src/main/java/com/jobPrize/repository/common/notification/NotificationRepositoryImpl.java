package com.jobPrize.repository.common.notification;

import java.time.LocalDateTime;
import java.util.List;

import com.jobPrize.entity.common.Notification;
import com.jobPrize.entity.common.QNotification;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Notification> findAllForOneMonthByUserId(Long id){	// 1개월 전까지의 모든 알림 조회
		QNotification notification = QNotification.notification;
		
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime oneMonthAgo = currentTime.minusMonths(1);
		
		List<Notification> results = queryFactory
				.selectFrom(notification)
				.join(notification.user).fetchJoin()
				.where(
						notification.user.id.eq(id),
						notification.createdTime.between(oneMonthAgo, currentTime)
						)
				.orderBy(notification.createdTime.desc())
				.fetch();
		
		return results;
	}
}
