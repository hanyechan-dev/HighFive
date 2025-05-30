package com.jobPrize.enumerate;

import com.jobPrize.entity.common.User;

public enum NotificationType {
    PROPOSAL("에서 채용 제안이 도착하였습니다."),
    APPROVAL_FEEDBACK("님의 피드백 요청을 승인하였습니다."),
    APPROVAL_EDIT("님의 첨삭 요청을 승인하였습니다."),
    COMPLETE_FEEDBACK("님이 요청하신 피드백 작업을 완료하였습니다."),
    COMPLETE_EDIT("님이 요청하신 첨삭 작업을 완료하였습니다."),
    ACCEPT("님께서 채용 제안을 수락하였습니다."),
    DECLINE("님께서 채용 제안을 거절하였습니다."),
    COMMENT("님이 댓글을 작성하셨습니다."),
	PASS("에 합격하였습니다.");
	
	private final String message;

	NotificationType(String label){
		this.message = label;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getMessageByType(User sender, User receiver) {
		return switch(this) {
			case PROPOSAL -> sender.getCompany().getCompanyName() + this.message;
			case PASS -> sender.getCompany().getCompanyName() + this.message;
			case APPROVAL_FEEDBACK, APPROVAL_EDIT, COMPLETE_FEEDBACK, COMPLETE_EDIT -> sender.getName() + " 컨설턴트가 " + receiver.getName() + this.message;
			case ACCEPT, DECLINE -> sender.getName() + this.message;
			case COMMENT -> receiver.getName() + "님께서 작성하신 게시글에 " + sender.getName() + this.message;
		};
	}
}
