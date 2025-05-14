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
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class ChatRoomRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private EntityManager em;
	
	@Test
	@Rollback(false)
	@DisplayName("채팅방 저장")
	void saveChatRooms() {
		List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
		List<User> testUsers = userRepository.findAllById(userIds);
		
		for(int i=0; i<9; i++) {
			ChatRoom chatRoom = ChatRoom
					.builder()
					.user1(testUsers.get(i))
					.user2(testUsers.get(i+1))
					.build();
			
			chatRoomRepository.save(chatRoom);
		}
        em.flush();
        em.clear();
	}
	
}
