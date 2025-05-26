package com.jobPrize.repository.common.subscription;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.common.QSubscription;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	// 모든 구독자 조회
	@Override
	public List<Subscription> findAll() {
		QSubscription subscription = QSubscription.subscription;
<<<<<<< HEAD
=======
		QUser user = QUser.user;
		LocalDate now = LocalDate.now();
>>>>>>> origin/origin/ADMIN01_SERVICE

		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.where(subscription.user.isSubscribed.eq(true))
				.leftJoin(subscription.user).fetchJoin()
				.distinct()
				.orderBy(subscription.startDate.desc())
				.fetch();
		return results;
	}
	
	// UserType에 따른 구독자 조회
	@Override
	public List<Subscription> findAllByUserType(UserType userType){
		QSubscription subscription = QSubscription.subscription;
		LocalDate now = LocalDate.now();
		
		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.leftJoin(subscription.user).fetchJoin()
				.where(subscription.user.type.eq(userType),
						subscription.user.isSubscribed.eq(true))
				.distinct()
				.orderBy(subscription.startDate.desc())
				.fetch();
		
		return results;
	}
}