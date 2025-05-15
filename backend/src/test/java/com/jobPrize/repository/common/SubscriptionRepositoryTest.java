package com.jobPrize.repository.common;

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
import com.jobPrize.entity.common.Subscription;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.subscription.SubscriptionRepository;
import com.jobPrize.repository.common.subscription.SubscriptionRepositoryImpl;

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
	
	@Autowired
	SubscriptionRepositoryImpl subscriptionRepositoryImpl;

//    @Test
//    @Rollback(false)
//    @DisplayName("구독 정보 저장")
//    void saveSubs() {
//    	List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
//    	List<User> testUsers = userRepository.findAllById(userIds);
//    	
//        for(int i=0; i<10; i++) {
//        	Subscription subscription = Subscription
//        			.builder()
//        			.user(testUsers.get(i))
//        			.build();
//
//            subscriptionRepository.save(subscription);
//        }
//        em.flush();
//        em.clear();
//    }
    
//    @Test
//    @Rollback(false)
//    @DisplayName("모든 구독자 조회")
//    void loadAllSubs() {
//    	List<Subscription> subscriptions = subscriptionRepositoryImpl.findAllSubs();
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    	
//    	System.out.println("모든 구독자 조회");
//    	System.out.println("----------------------------");
//    	for(Subscription subs: subscriptions) {
//    		System.out.println("사용자명 : " + subs.getUser().getName());
//    		System.out.println("구독일자 : " + subs.getStartDate().format(formatter));
//    		System.out.println("해지일자 : " + subs.getEndDate().format(formatter));
//    		System.out.println();
//    	}
//    }
    
    @Test
    @Rollback(false)
    @DisplayName("UserType에 따른 구독자 조회")
    void loadSubsByUserType() {
    	List<Subscription> subsByUserType = subscriptionRepositoryImpl.findAllByUserType(UserType.일반회원);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String userTypeString = subsByUserType.get(0).getUser().getType().name();
    	
    	System.out.println(userTypeString + " 구독자 조회");
    	System.out.println("----------------------------");
    	for(Subscription subs: subsByUserType) {
    		System.out.println("사용자명 : " + subs.getUser().getName());
    		System.out.println("구독일자 : " + subs.getStartDate().format(formatter));
    		System.out.println("해지일자 : " + subs.getEndDate().format(formatter));
    		System.out.println();
    	}
    }

}