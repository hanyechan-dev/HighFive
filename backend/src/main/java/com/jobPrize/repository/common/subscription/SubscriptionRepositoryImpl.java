package com.jobPrize.repository.common.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.common.QSubscription;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.enumerate.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	// 모든 구독자 조회
	@Override
	public List<Subscription> findAll() {
		QSubscription subscription = QSubscription.subscription;

		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.where(subscription.user.subscribed.eq(true))
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
		
		List<Subscription> results = queryFactory
				.selectFrom(subscription)
				.leftJoin(subscription.user).fetchJoin()
				.where(subscription.user.type.eq(userType),
						subscription.user.subscribed.eq(true))
				.distinct()
				.orderBy(subscription.startDate.desc())
				.fetch();
		
		return results;
	}
	
	// UserType에 따른 구독자 수 조회
	@Override
	public Long countSubsByUserType(UserType userType){
		QSubscription subscription = QSubscription.subscription;
		LocalDate today = LocalDate.now();
		
		Long result = queryFactory
				.select(subscription.count())
				.from(subscription)
				.leftJoin(subscription.user)
				.where(
						subscription.user.type.eq(userType)
						.and(subscription.user.subscribed.eq(true))
						)
				.fetchOne();
		
		return result;
	}

	@Override
	public Optional<Subscription> findLatestByUserId(Long id) {
		QSubscription subscription = QSubscription.subscription;
		
		
		Subscription result = queryFactory
				.selectFrom(subscription)
				.join(subscription.user).fetchJoin()
				.where(subscription.user.id.eq(id))
				.orderBy(subscription.startDate.desc())
				.fetchFirst();
		
		 return Optional.ofNullable(result);
	}

	@Override
	public Optional<Long> findUserIdBySubscriptionId(Long id) {
		QSubscription subscription = QSubscription.subscription;
		
		Long result = queryFactory
				.select(subscription.user.id)
				.from(subscription)
				.where(subscription.id.eq(id))
				.fetchOne();
		
		 return Optional.ofNullable(result);
	}
}