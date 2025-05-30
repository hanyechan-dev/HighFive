package com.jobPrize.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.token.TokenDto;
import com.jobPrize.dto.common.user.login.LogInDto;
import com.jobPrize.service.common.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	@PostMapping("/token")
	public ResponseEntity<TokenDto> logIn(@RequestBody @Valid LogInDto logInDto) {
		
		TokenDto tokenDto = userService.logIn(logInDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
		
	}

}
