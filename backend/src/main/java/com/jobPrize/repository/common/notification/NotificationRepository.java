package com.jobPrize.repository.common.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {
}
