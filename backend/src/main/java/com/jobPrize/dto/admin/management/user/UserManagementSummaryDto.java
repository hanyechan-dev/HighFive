package com.jobPrize.dto.admin.management.user;

import java.time.LocalDate;

import com.jobPrize.entity.common.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserManagementSummaryDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String address;
    private LocalDate createdDate;

    public static UserManagementSummaryDto from(User user){
    	
        return UserManagementSummaryDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
