package com.jobPrize.service;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.memberService.dto.signUp.MemberSignUpDto;
import com.jobPrize.memberService.dto.signUp.UserSignUpDto;
import com.jobPrize.memberService.service.commom.UserService;

@SpringBootTest
@Rollback(false)
@DisplayName("UserService 통합 테스트")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void saveUserTest() {
        UserSignUpDto dto = UserSignUpDto.builder()
                .email("kang2@naver.com")
                .password("123456")
                .name("김탁봉")
                .birthDate(LocalDate.now())
                .genderType(GenderType.남자)
                .phone("01012345678")
                .address("대전")
                .type(UserType.일반회원)
                .build();

        String token = userService.signUpUser(dto);
        System.out.println("토큰: " + token);
    }
    
    @Test
    void saveMemberTest() {
        MemberSignUpDto dto = new MemberSignUpDto("닉네임");
        
        userService.registerMemberInfo(dto,"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwidXNlclR5cGUiOiLsnbzrsJjtmozsm5AiLCJpYXQiOjE3NDc1NTQ3OTEsImV4cCI6MTc0NzU2MTk5MX0.AgcSztc8YWz-Z_qJ-i40TIltMP74vhwpIV1qoHXbc_I");

    }
    
    
}

