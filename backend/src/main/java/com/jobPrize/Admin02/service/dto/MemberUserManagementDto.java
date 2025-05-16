package com.jobPrize.Admin02.service.dto;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.member.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUserManagementDto {
    private Long id;
    private String email;
    private String name;
    private String nickName;
    private String phone;
    private String address;
    private LocalDate createdDate;

    public static MemberUserManagementDto from(Member member){
    	User user = member.getUser();
    	
        return MemberUserManagementDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickName(member.getNickname())
                .phone(user.getPhone())
                .address(user.getAddress())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
