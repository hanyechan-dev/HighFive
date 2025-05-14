package com.jobPrize.repository.common;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.subscription.SubscriptionRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
class SubscriptionRepositoryTest {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager em;

    @Test
    @Rollback(false)
    @DisplayName("구독 정보 저장")
    void savePayments() {
    	List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
    	List<User> testUsers = userRepository.findAllById(userIds);
    	
        for(int i=0; i<10; i++) {
        	Subscription subscription = Subscription
        			.builder()
        			.user(testUsers.get(i))
        			.build();

            subscriptionRepository.save(subscription);
        }
        em.flush();
        em.clear();
    }

}