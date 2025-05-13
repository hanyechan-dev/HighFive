package com.jobPrize.repository.common.subscription;

import java.util.List;

import com.jobPrize.entity.common.QSubscription;
import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Subscription> findAll() {	// 모든 구독자 조회
		QSubscription subscription = QSubscription.subscription;
		
		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.orderBy(subscription.startDate.desc())
				.fetch();
		return results;
	}
	
	@Override
	public List<Subscription> findAllByUserType(UserType userType){ // 사용자 유형에 따른 구독자 정보 조회
		QSubscription subscription = QSubscription.subscription;
		QUser user = QUser.user;
		
		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.join(subscription.user, user).fetchJoin()
				.where(user.type.eq(userType))
				.orderBy(subscription.startDate.desc())
				.fetch();
		
		return results;
	}
}