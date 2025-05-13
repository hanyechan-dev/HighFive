package com.jobPrize.entity.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.entity.common.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscription")			// 구독 테이블
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBSCRIBE_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
	
	@CreatedDate
    @Column(name = "START_DATE", nullable = false)
<<<<<<< HEAD
    private LocalDateTime startDate;
	
	@CreatedDate
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;
=======
    private LocalDateTime startDate;	// 구독 시작일
	
	@CreatedDate
    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;	//	구독 종료일
>>>>>>> origin/ADMIN01_REPOSITORY
}
