package com.jobPrize.repository.admin.payment;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.payment.PaymentRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
class PaymentRepositoryTest {

	@Autowired
	private PaymentRepository paymentRepository;

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager em;

//    @Test
//    @Rollback(false)
//    @DisplayName("결제 내역 저장")
//    void savePayment() {
//    	List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
//    	List<User> testUsers = userRepository.findAllById(userIds);
//    	
//        for(int i=0; i<10; i++) {
//        	Payment payment = Payment
//        			.builder()
//        			.user(testUsers.get(i))
//        			.paymentAmount(9800)
//        			.content("일반회원 구독료")
//        			.method("카드")
//        			.build();
//
//            adminPaymentRepository.save(payment);
//        }
//        em.flush();
//        em.clear();
//    }
    
    @Test
    @Rollback(false)
    @DisplayName("지정된 기간 및 사용자 유형에 따른 매출 통계")
    void loadSales() {

    	LocalDateTime start = LocalDateTime.of(2025, Month.MAY, 14, 2, 15);
    	LocalDateTime end = LocalDateTime.of(2025, Month.MAY, 15, 22, 35);
    	List<Payment> paidUsers = paymentRepository.findAllByUserTypeAndPeriod(start, end, UserType.일반회원);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    	
    	System.out.println("결제 내역을 조회합니다.");
    	System.out.println("검색 기간 : " + start.format(formatter) + " - " + end.format(formatter));
    	System.out.println("---------------------------------------");
    	for(Payment payment : paidUsers) {
    		System.out.println("사용자명 : " + payment.getUser().getName());
    		System.out.println("결제금액 : " + payment.getPaymentAmount());
    		System.out.println("결제내용 : " + payment.getContent());
    		System.out.println("결제방법 : " + payment.getMethod());
    		System.out.println("결제시각 : " + payment.getCreatedTime().format(formatter));
    		System.out.println();
    	}
    
    }
    

}