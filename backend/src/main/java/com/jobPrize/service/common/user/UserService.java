package com.jobPrize.service.common.user;

import com.jobPrize.dto.common.myPage.MyPageResponseDto;
import com.jobPrize.dto.common.myPage.MyPageUpdateDto;
import com.jobPrize.dto.common.myPage.PasswordUpdateDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.dto.common.user.kakao.KakaoUserSignUpDto;
import com.jobPrize.dto.common.user.login.LogInDto;
import com.jobPrize.dto.common.user.signUp.UserSignUpDto;
import com.jobPrize.enumerate.UserType;

public interface UserService {
	public TokenDto createUser(UserSignUpDto userSignUpDto);
	public TokenDto logIn(LogInDto logInDto);
	public MyPageResponseDto readUserMyPageInfo(Long id);
	public void updateUserMyPageInfo(Long id, MyPageUpdateDto myPageUpdateDto);
	public void updateUserPassword(Long id, PasswordUpdateDto passwordUpdateDto);
	public void softDeleteUser(Long id, UserType userType, Long targetId);
	
	public boolean isExistEmail(String email);
	
	public TokenDto createUserForKakao(KakaoUserSignUpDto kakaoUserSignUpDto);
	public TokenDto logInForKakao(String email);
	
	public String getEmailFromKakaoAccessToken(String kakaoAccessToken);
		
}
