package com.jobPrize.service.common.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.jwt.TokenProvider;
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

	private final TokenProvider tokenProvider;

	private final AssertUtil assertUtil;

	private static final String ENTITY_NAME = "구독";

	// 구독자 생성
	@Override
	public TokenDto createSubscription(Long id, UserType userType, PaymentRequestDto paymentRequestDto) {

		String action = "수행";

		assertUtil.assertUserType(userType, UserType.일반회원, UserType.기업회원, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		if (user.isSubscribed()) {
			throw new CustomIllegalArgumentException("이미 구독한 회원입니다.");
		}

		LocalDate now = LocalDate.now();

		Subscription subscription = Subscription.builder().user(user).startDate(now).endDate(now.plusMonths(1))
				.unsubscribeScheduled(false).build();

		subscriptionRepository.save(subscription);

		user.subscribe(); // 구독 중인 회원으로 상태 변경

		userRepository.save(user);

		paymentService.createPayment(id, userType, paymentRequestDto);

		String accessToken = tokenProvider.createAccessToken(user.getId(), user.getUserType(), user.getApprovalStatus(),
				user.isSubscribed());
		String refreshToken = tokenProvider.createRefreshToken(user.getId(), user.getUserType(),
				user.getApprovalStatus(), user.isSubscribed());

		return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

	}

	// 사용자 유형에 따른 구독자 조회
	@Override
	@Transactional(readOnly = true)
	public List<SubscriptionResponseDto> readSubscriberListByUserType(UserType userType, UserType targetUserType) {

		String action = "조회";

		assertUtil.assertUserType(userType, UserType.관리자, ENTITY_NAME, action);

		List<Subscription> subscribers = subscriptionRepository.findAllByUserType(targetUserType);

		return subscribers.stream()
				.map(subs -> SubscriptionResponseDto.builder().id(subs.getUser().getId()).name(subs.getUser().getName())
						.userType(subs.getUser().getUserType().name()).startDate(subs.getStartDate())
						.endDate(subs.getEndDate()).build())
				.collect(Collectors.toList());
	}

	// 사용자 유형에 따른 구독자 수 조회
	@Override
	@Transactional(readOnly = true)
	public List<SubscriptionResponseDto> ReadSubscribersCountByUserType(UserType userType, UserType targetUserType) {

		String action = "조회";

		assertUtil.assertUserType(userType, UserType.관리자, ENTITY_NAME, action);

		List<Subscription> subscribers = subscriptionRepository.findAllByUserType(targetUserType);

		return subscribers.stream()
				.map(subs -> SubscriptionResponseDto.builder().id(subs.getUser().getId()).name(subs.getUser().getName())
						.userType(subs.getUser().getUserType().name()).startDate(subs.getStartDate())
						.endDate(subs.getEndDate()).build())
				.collect(Collectors.toList());
	}

	// 구독 만료 시, 구독 상태 자동 업데이트
	@Override
	@Scheduled(cron = "0 0 0 * * *") // 매일마다 실행
	@Transactional
	public void updateStatus() {
		LocalDate now = LocalDate.now();

		List<Subscription> subs = subscriptionRepository.findAllByUnsubscribeScheduledIsTrue(); // 구독 해지 플래그가 켜진 구독만 조회

		List<Subscription> expired = subs.stream()
				.filter(expiredSub -> expiredSub.getEndDate().isBefore(now) || expiredSub.getEndDate().isEqual(now))
				.collect(Collectors.toList());

		for (Subscription sub : expired) {
			User user = sub.getUser();
			if (user.isSubscribed())
				user.unSubscribe();

			userRepository.save(user);
		}

	}

	// 사용자가 자신의 마지막 구독 정보 조회
	@Override
	@Transactional(readOnly = true)
	public SubscriptionResponseDto readSubscription(Long id) {

		String action = "조회";

		Subscription subscription = subscriptionRepository.findLatestByUserId(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		return SubscriptionResponseDto.builder().id(subscription.getUser().getId())
				.name(subscription.getUser().getName()).userType(subscription.getUser().getUserType().name())
				.startDate(subscription.getStartDate()).endDate(subscription.getEndDate())
				.unsubscribeScheduled(subscription.isUnsubscribeScheduled()).build();

	}

	@Override
	public SubscriptionResponseDto scheduleUnsubscribe(Long id) {

		String action = "구독 해지 예약";

		Long ownerId = subscriptionRepository.findUserIdBySubscriptionId(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);

		Subscription subscription = subscriptionRepository.findLatestByUserId(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		if (subscription.isUnsubscribeScheduled()) {
			throw new CustomIllegalArgumentException("이미 구독 해지가 신청되었습니다.");
		}

		subscription.scheduleUnsubscribe();

		return SubscriptionResponseDto.builder().id(subscription.getUser().getId())
				.name(subscription.getUser().getName()).userType(subscription.getUser().getUserType().name())
				.startDate(subscription.getStartDate()).endDate(subscription.getEndDate())
				.unsubscribeScheduled(subscription.isUnsubscribeScheduled()).build();

	}
}
