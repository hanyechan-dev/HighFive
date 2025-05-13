package com.jobPrize.repository.admin01.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
}
