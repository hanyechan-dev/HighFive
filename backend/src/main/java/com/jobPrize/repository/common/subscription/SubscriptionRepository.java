package com.jobPrize.repository.common.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {
	 Optional<Subscription> findByUserId(Long userId);
}
