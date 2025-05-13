package com.jobPrize.repository.admin01.Subscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {
}