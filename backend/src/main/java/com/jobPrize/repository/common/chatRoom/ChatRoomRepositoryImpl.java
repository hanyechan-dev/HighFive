package com.jobPrize.repository.common.chatRoom;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.QChatContent;
import com.jobPrize.entity.common.QChatRoom;
import com.jobPrize.entity.common.QUser;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	// 채팅방 리스트 조회(가장 최근 메시지의 시간을 기준으로 내림차순 정렬)
	@Override
	public List<ChatRoom> findAllByUserId(Long id){	
		QChatRoom chatRoom = QChatRoom.chatRoom;
		QChatContent chatContent = QChatContent.chatContent;
		
		QUser user1 = new QUser("user1_alias");
		QUser user2 = new QUser("user2_alias");
		
	    OrderSpecifier<?> orderByLatestChat = new OrderSpecifier<>(
	            Order.DESC,
	            JPAExpressions
	                .select(chatContent.createdTime.max())
	                .from(chatContent)
	                .where(chatContent.chatRoom.eq(chatRoom))
	        );
		
		List<ChatRoom> results = queryFactory
				.selectFrom(chatRoom)
				.leftJoin(chatRoom.user1, user1).fetchJoin()
				.leftJoin(chatRoom.user2, user2).fetchJoin()
				.leftJoin(user1.member).fetchJoin()
				.leftJoin(user2.member).fetchJoin()
				.leftJoin(user1.company).fetchJoin()
				.leftJoin(user2.company).fetchJoin()
				.leftJoin(user1.consultant).fetchJoin()
				.leftJoin(user2.consultant).fetchJoin()
				.where(
						chatRoom.user1.id.eq(id)
						.or(chatRoom.user2.id.eq(id))
						)
				.orderBy(orderByLatestChat.nullsLast())
				.fetch();
		
		return results;
	}
	
	// 채팅방 번호를 통해 채팅 내용과 연결
	@Override
	public Optional<ChatRoom> findWithChatContentsByChatRoomId(Long id){
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
	
	// 채팅방 소속 여부 확인
	@Override
	public Boolean checkMemberInChatRoom(Long id, Long roomId) {	
		QChatRoom chatRoom = QChatRoom.chatRoom;
		
		ChatRoom result = queryFactory
				.selectFrom(chatRoom)
				.leftJoin(chatRoom.user1).fetchJoin()
				.leftJoin(chatRoom.user2).fetchJoin()
				.where(
					chatRoom.id.eq(roomId)
					.and(
						chatRoom.user1.id.eq(id)
						.or(chatRoom.user2.id.eq(id))
					)
				)
				.fetchOne();
		
		return result != null;
	}
	
	// 두 사용자가 속한 채팅방 검색
	@Override
	public ChatRoom findChatRoomWithTwoUsers(Long id, Long targetId) {
		QChatRoom chatRoom = QChatRoom.chatRoom;
		
		ChatRoom result = queryFactory
				.selectFrom(chatRoom)
				.leftJoin(chatRoom.user1).fetchJoin()
				.leftJoin(chatRoom.user2).fetchJoin()
				.where(
					(
							chatRoom.user1.id.eq(id)
							.and(chatRoom.user2.id.eq(targetId))
					)
					.or(
							chatRoom.user1.id.eq(targetId)
							.and(chatRoom.user2.id.eq(id))
					)
					)
				.fetchOne();
		
		return result;
	}
	
}
