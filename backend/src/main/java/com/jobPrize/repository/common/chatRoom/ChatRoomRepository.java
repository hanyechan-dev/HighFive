package com.jobPrize.repository.common.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
}
