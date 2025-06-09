package com.jobPrize.dto.common.chat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InviteTargetDto {
	
    @NotNull(message = "상대방 ID는 비어 있을 수 없습니다.")
    private Long targetId;
    
    @NotNull(message = "채팅방 정보는 비어 있을 수 없습니다.")
    private Long chatRoomId;
}
