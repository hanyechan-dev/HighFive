package com.jobPrize.service.common.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.subscription.SubscriptionRepository;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.service.common.payment.PaymentService;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;
	private final UserRepository userRepository;
	private final PaymentService paymentService;
	private final AssertUtil assertUtil;
	
	// 구독자 생성
	@Override
	public void createSubscription(Long id, UserType userType, PaymentRequestDto paymentRequestDto) {
		
		assertUtil.assertUserType(userType, UserType.일반회원, UserType.기업회원, "구독");
		
		LocalDate now = LocalDate.now();
		
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		
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
	public List<SubscriptionResponseDto> readSubscriberListByUserType(UserType userType, UserType targetUserType) {
		
		assertUtil.assertUserType(userType, UserType.관리자, "조회");
		
		List<Subscription> subscribers = subscriptionRepository.findAllByUserType(targetUserType);
		
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
	@Override
	@Transactional(readOnly = true)
	public SubscriptionResponseDto readSubscription(Long id) { 

		Subscription subscription = subscriptionRepository.findLatestByUserId(id) 
				.orElseThrow(() -> new CustomEntityNotFoundException("구독"));
		    
		assertUtil.assertId(id, subscription, "구독 정보 조회");

		return SubscriptionResponseDto.builder()
				.id(subscription.getUser().getId())
		        .name(subscription.getUser().getName())
		        .userType(subscription.getUser().getType())
		        .startDate(subscription.getStartDate())
	            .endDate(subscription.getEndDate())
	            .build();

		}
	
}
