package com.jobPrize.repository.common.chatRoom;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.QChatContent;
import com.jobPrize.entity.common.QChatRoom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<ChatRoom> findAll(){	// 채팅방 리스트 조회(가장 최근 메시지의 시간을 기준으로 내림차순 정렬)
		QChatRoom chatRoom = QChatRoom.chatRoom;
		QChatContent chatContent = QChatContent.chatContent;
		
	    OrderSpecifier<?> orderByLatestChat = new OrderSpecifier<>(
	            Order.DESC,
	            JPAExpressions
	                .select(chatContent.createdTime.max())
	                .from(chatContent)
	                .where(chatContent.chatRoom.eq(chatRoom))
	        );
		
		List<ChatRoom> results = queryFactory
				.selectFrom(chatRoom)
				.orderBy(orderByLatestChat.nullsLast())
				.fetch();
		
		return results;
	}
	
	@Override
	public Optional<ChatRoom> findWithChatContentsByChatRoomId(Long id){	// 채팅방 번호를 통해 채팅 내용과 연결
		QChatRoom chatRoom = QChatRoom.chatRoom;
		QChatContent chatContent = QChatContent.chatContent;
		
		ChatRoom result = queryFactory
				.selectFrom(chatRoom)
				.leftJoin(chatRoom.chatContents, chatContent).fetchJoin()
				.where(chatRoom.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}
}
