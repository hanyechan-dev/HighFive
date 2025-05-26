package com.jobPrize.admin01_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.SubscriptionResponseDto;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.subscription.SubscriptionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;
	private final UserRepository userRepository;
	private final PaymentService paymentService;
	
	// 구독자 생성
	@Override
	public void createSubscription(Long id,UserType userType,PaymentRequestDto paymentRequestDto) {
		
		// 유저 타입 권한검사 필요
		
		LocalDate now = LocalDate.now();
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No User Found"));
		
		Subscription subscription = Subscription.builder()
				.user(user)
				.startDate(now)
				.endDate(now.plusMonths(1))
				.build();
		
		subscriptionRepository.save(subscription);
		
		user.subscribe();	// 구독 중인 회원으로 상태 변경
		
		userRepository.save(user);
		
		paymentService.createPayment(id, userType, paymentRequestDto);
		
		
	}
	
	// 사용자 유형에 따른 구독자 조회
	@Override
	@Transactional(readOnly = true)
	public List<SubscriptionResponseDto> readSubscriberListByUserType(UserType userType) {
		
		List<Subscription> subscribers = subscriptionRepository.findAllByUserType(userType);
		
		return subscribers.stream()
			.map(subs -> SubscriptionResponseDto.builder()
					.id(subs.getUser().getId())
					.name(subs.getUser().getName())
					.userType(subs.getUser().getType())
					.startDate(subs.getStartDate())
					.endDate(subs.getEndDate())
					.build()
				)
			.collect(Collectors.toList());
	}

	// 구독 만료 시, 구독 상태 자동 업데이트
	@Override
	@Scheduled(cron = "0 0 0 * * *") // 매일마다 실행
	public void updateStatus() {
		LocalDate now = LocalDate.now();
		
		List<Subscription> subs = subscriptionRepository.findAll();
		
		List<Subscription> expired = subs.stream()
		.filter(expiredSub -> expiredSub.getEndDate().isBefore(now) || expiredSub.getEndDate().isEqual(now))
		.collect(Collectors.toList());
		
		for(Subscription sub : expired) {
			User user = sub.getUser();
			if(user.isSubscribed())
				user.unSubscribe();
			
			userRepository.save(user);
		}

	}
	
}
