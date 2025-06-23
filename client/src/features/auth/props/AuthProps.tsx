export interface LogInDto {
	email: string;
	password: string;
}

export interface KakaoAccessTokenDto {
	kakaoAccessToken: string;
}

export interface UserSignUpDto {
	email: string;
	password: string;
	name: string;
	birthDate: string;
	genderType: string;
	phone: string;
	address: string;
	userType: string;
}

export interface KakaoUserSignUpDto {
	email: string;
	name: string;
	birthDate: string;
	genderType: string;
	phone: string;
	address: string;
	userType: string;
}

export interface MemberCreateDto {
	nickname: string;
}
