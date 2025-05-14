package com.jobPrize.repository.admin.payment;

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
import com.jobPrize.entity.common.User;
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

    @Test
    @Rollback(false)
    @DisplayName("결제 내역 저장")
    void savePayments() {
    	List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
    	List<User> testUsers = userRepository.findAllById(userIds);
    	
        for(int i=0; i<10; i++) {
        	Payment payment = Payment
        			.builder()
        			.user(testUsers.get(i))
        			.paymentAmount(9800)
        			.content("일반회원 구독료")
        			.method("카드")
        			.build();

            paymentRepository.save(payment);
        }
        em.flush();
        em.clear();
    }

}