package com.jobPrize.repository.common.subscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {
	
	List<Subscription> findAllByUnsubscribeScheduledIsTrue();
}
