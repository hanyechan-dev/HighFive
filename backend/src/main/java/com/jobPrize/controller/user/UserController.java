package com.jobPrize.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.common.myPage.MyPageUpdateDto;
import com.jobPrize.dto.common.myPage.PasswordUpdateDto;
import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.dto.common.user.kakao.KakaoUserSignUpDto;
import com.jobPrize.dto.common.user.signUp.UserSignUpDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.common.user.UserService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<TokenDto> signUp(@RequestBody @Valid UserSignUpDto userSignUpDto) {
		
		TokenDto tokenDto = userService.createUser(userSignUpDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
		
	}
	
	@PostMapping("/kakao")
	public ResponseEntity<TokenDto> signUpForKakao(@RequestBody @Valid KakaoUserSignUpDto kakaoUserSignUpDto) {
		
		TokenDto tokenDto = userService.createUserForKakao(kakaoUserSignUpDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
		
	}
	
	
	@PutMapping("/me")
	public ResponseEntity<Void> updateMyPage(@RequestBody @Valid MyPageUpdateDto myPageUpdateDto){
		
		Long id = SecurityUtil.getId();
		
		userService.updateUserMyPageInfo(id, myPageUpdateDto);
		
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/me/password")
	public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordUpdateDto passwordUpdateDto){
		
		Long id = SecurityUtil.getId();
		
		userService.updateUserPassword(id, passwordUpdateDto);
		
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/me/deactivation")
	public ResponseEntity<Void> deactivateAccount(@RequestBody IdDto idDto){
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		
		Long targetId = idDto.getId();
		userService.softDeleteUser(id, userType, targetId);
		
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	
	


}
