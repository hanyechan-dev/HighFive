package com.jobPrize.service.common.payment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.payment.KakaoReadyResponseDto;
import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.payment.PaymentResponseDto;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.payment.PaymentRepository;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	private final UserRepository userRepository;
	
	private final RestTemplate restTemplate;
	
	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "결제";
	
	@Transactional
	@Override
	public void createPayment(Long id, UserType userType, PaymentRequestDto paymentRequestDto) {
		
		String action = "수행";
		
		assertUtil.assertUserType(userType, UserType.일반회원, UserType.기업회원, ENTITY_NAME, action);
		
		// * 추후, 이 곳에 결제 시스템 구현 필수.
		// 쇼핑몰처럼 장바구니, 상품, 주문 등 복잡한 로직이 필요없이,
		// 결제 상태에 따라 User의 isSubscribed 데이터를 변경하면 되는 문제로 보임.
		
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

			
		// 결제 정보 저장
		Payment payment = Payment.builder()
				.user(user)
				.paymentAmount(paymentRequestDto.getPaymentAmount())
				.content(paymentRequestDto.getContent())
				.method(paymentRequestDto.getMethod())
				.build();
		
		paymentRepository.save(payment);

	}
	
	// ID별 결제 내역 리스트 조회
	@Override
	public Page<PaymentResponseDto> readPaymentPageById(Long id, Pageable pageable) {
		Page<Payment> paymentPage = paymentRepository.findAllByUserId(id, pageable);
		List<Payment> paymentList = paymentPage.getContent();
		
		
		List<PaymentResponseDto> paymentResponseDtos = paymentList.stream()
			.map(payment -> PaymentResponseDto.builder()
				.paymentId(payment.getId())
				.paymentAmount(payment.getPaymentAmount())
				.createdTime(payment.getCreatedTime())
				.content(payment.getContent())
				.method(payment.getMethod())
				.build()
				)
			
			.collect(Collectors.toList());
		 
		 return new PageImpl<>(paymentResponseDtos,pageable, paymentPage.getTotalElements());
	}
	
	// 전체 결제 내역 리스트 조회
	@Override
	public List<PaymentResponseDto> readPaymentList() {
		List<Payment> paymentList = paymentRepository.findAll();
		
		return paymentList.stream()
			.map(payment -> PaymentResponseDto.builder()
				.paymentId(payment.getId())
				.id(payment.getUser().getId())
				.paymentAmount(payment.getPaymentAmount())
				.content(payment.getContent())
				.createdTime(payment.getCreatedTime())
				.method(payment.getMethod())
				.build()
				)
			
			.collect(Collectors.toList());
	}

	@Override
	public KakaoReadyResponseDto readyToKakaoPay(Long paymentId, Long id) {
		HttpHeaders headers = new HttpHeaders();
		
		
		
		return null;
	}
	
}
