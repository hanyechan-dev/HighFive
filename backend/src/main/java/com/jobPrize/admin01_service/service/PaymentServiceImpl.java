package com.jobPrize.admin01_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.PaymentResponseDto;
import com.jobPrize.admin01_service.dto.SubscriptionRequestDto;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.payment.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;
	private final UserRepository userRepository;
	private final SubscriptionService subscriptionService;
	
	@Transactional
	@Override
	public void createPayment(PaymentRequestDto paymentRequestDto) throws Exception {
		
		// * 추후, 이 곳에 결제 시스템 구현 필수.
		// 쇼핑몰처럼 장바구니, 상품, 주문 등 복잡한 로직이 필요없이,
		// 결제 상태에 따라 User의 isSubscribed 데이터를 변경하면 되는 문제로 보임.
		
		
		// 결제 정보 저장
		Payment payment = Payment.builder()
				.user(userRepository.findById(paymentRequestDto.getId())
						.orElseThrow(() -> new EntityNotFoundException("User Not Found")))
				.paymentAmount(paymentRequestDto.getPaymentAmount())
				.content(paymentRequestDto.getContent())
				.method(paymentRequestDto.getMethod())
				.build();
		
		paymentRepository.save(payment);
		
		// 구독 상태 변경
		Long userId = paymentRequestDto.getId();
		
		SubscriptionRequestDto subscriptionRequestDto = SubscriptionRequestDto.builder()
				.id(userId)
				.build();
		
		subscriptionService.createSubscription(subscriptionRequestDto);
	}
	
	// 결제 내역 조회
	@Override
	public List<PaymentResponseDto> readPayment(Long id, Pageable pageable) throws Exception {
		Page<Payment> paymentPage = paymentRepository.findAllByUserId(id, pageable);
		List<Payment> paymentList = paymentPage.getContent();
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User Not Found"));
		UserType userType = user.getType();
		
		return paymentList.stream()
			.map(payment -> PaymentResponseDto.builder()
				.userType(userType)
				.paymentId(payment.getId())
				.paymentAmount(payment.getPaymentAmount())
				.createdTime(payment.getCreatedTime())
				.build()
				)
			.collect(Collectors.toList());
	}
	
}
