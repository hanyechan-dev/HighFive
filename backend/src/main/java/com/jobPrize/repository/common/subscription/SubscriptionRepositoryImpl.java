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
		QUser user = QUser.user;

		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.leftJoin(subscription.user).fetchJoin()
				.distinct()
				.orderBy(subscription.startDate.desc())
				.fetch();
		return results;
	}
	
	@Override
	public List<Subscription> findAllByUserType(UserType userType){ // UserType에 따른 구독자 조회
		QSubscription subscription = QSubscription.subscription;
		
		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.join(subscription.user).fetchJoin()
				.where(subscription.user.type.eq(userType))
				.distinct()
				.orderBy(subscription.startDate.desc())
				.fetch();
		
		return results;
	}
}