package com.jobPrize.admin01_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.admin01_service.dto.SubscriptionRequestDto;
import com.jobPrize.admin01_service.dto.SubscriptionResponseDto;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.subscription.SubscriptionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
	private final TokenProvider tokenProvider;
	private final SubscriptionRepository subscriptionRepository;
	private final UserRepository userRepository;
	

	
	@Transactional
	@Override
	public void createSubscription(SubscriptionRequestDto subscriptionRequestDto) throws Exception {
		LocalDate now = LocalDate.now();
		
		Subscription subscription = Subscription.builder()
				.user(userRepository.findById(subscriptionRequestDto.getId())
						.orElseThrow(() -> new EntityNotFoundException("No Subscription Found")))
				.startDate(now)
				.endDate(now.plusMonths(1))
				.build();
		
		subscriptionRepository.save(subscription);
		
		User user = userRepository.findById(subscriptionRequestDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("No User Found"));
		
		// 구독 중인 회원으로 상태 변경
		user.subscribe();
		
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<SubscriptionResponseDto> readSubscriberByUserTypeList(UserType userType) {
		
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

	// 구독이 만료되면(현재 일자보다 만료 일자의 값이 크면), 자동적으로 isSubscribed가 false로 바뀌고 구독자 List에서 보이지 않아야 함.
	
}
