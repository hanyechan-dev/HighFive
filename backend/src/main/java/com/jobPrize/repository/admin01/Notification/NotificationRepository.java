package com.jobPrize.repository.admin01.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {
}
