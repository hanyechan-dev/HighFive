import { useDispatch } from "react-redux";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { companyInfoInputApi, kakaoLoginApi, kakaoSignUpApi, loginApi, nicknameInputApi, SignUpApi } from "../apis/AuthApi";
import { setToken } from "../slices/AuthSlice";
import { useAuthController } from "./useAuthController";
import type { KakaoUserSignUpDto, LogInDto, MemberCreateDto, UserSignUpDto } from "../props/AuthProps";
import axios from "axios";
import { closeAuthModal } from "../../../common/slices/AuthModalSlice";

interface KakaoAuthSuccess {
    access_token: string;
    expires_in: number;
    refresh_token: string;
    refresh_token_expires_in: number;
    scope: string;
    token_type: string;
}



export const useAuthApi = () => {
    const dispatch = useDispatch();

    const { setIsKakao, setKakaoEmail, setShowModalType } = useAuthController();

    const login = async (loginDto: LogInDto) => {
        try {
            const res = await loginApi(loginDto);
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));
            return true
        } catch (err) {
            printErrorInfo(err);
            return false
        }
    };

    const nicknameInput = async (nicknameInputDto: MemberCreateDto) => {
        try {
            await nicknameInputApi(nicknameInputDto);
            return true
        } catch (err) {
            printErrorInfo(err);
            return false
        }

    }

    const signUp = async (signUpDto: UserSignUpDto) => {
        try {
            const res = await SignUpApi(signUpDto);
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));
            return true
        } catch (err) {
            printErrorInfo(err);
            return false
        }
 
    }

    const kakaoLogin = () => {
        if (!window.Kakao.isInitialized()) return;

        window.Kakao.Auth.login({
            success: async (authObj: KakaoAuthSuccess) => {
                console.log('카카오 로그인 성공:', authObj);
                const kakaoAccessToken = authObj.access_token;

                try {
                    const res = await kakaoLoginApi({ kakaoAccessToken });

                    if (res.status === 200 && res.data.accessToken) {
                        const { accessToken, refreshToken } = res.data;
                        dispatch(setToken({ accessToken, refreshToken }));
                        dispatch(closeAuthModal())
                    }
                }
                catch (err: unknown) {
                    if (axios.isAxiosError(err)) {
                        if (err.response?.status === 404) {
                            const email = err.response.data;
                            console.log(email)
                            setKakaoEmail(email);
                            setIsKakao(true);
                            setShowModalType("signUp");
                            

                        }
                    }
                    else {
                        printErrorInfo(err);
                    }
                }


            },
            fail: function (err: unknown) {
                console.error('카카오 로그인 실패:', err);
            }
        });
    };

    const companyInfoInput = async (
        companyName: string,
        businessNumber: string,
        representativeName: string,
        type: string,
        companyAddress: string,
        companyPhone: string,
        industry: string,
        employeeCount: string,
        establishedDate: string,
        introduction: string,
        logoImageFile: File | null) => {
        try {
            await companyInfoInputApi(companyName,
                businessNumber,
                representativeName,
                type,
                companyAddress,
                companyPhone,
                industry,
                employeeCount,
                establishedDate,
                introduction,
                logoImageFile);

            return true
        } catch (err) {
            printErrorInfo(err);
            return false
        }
    }

    const kakaoSignUp = async (kakaoSignUpDto: KakaoUserSignUpDto) => {
        try {
            const res = await kakaoSignUpApi(kakaoSignUpDto);
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));
            return true
        } catch (err) {
            printErrorInfo(err);
            return false
        }
 
    }


    return {
        login,
        nicknameInput,
        signUp,
        kakaoLogin,
        companyInfoInput,
        kakaoSignUp
    }
}