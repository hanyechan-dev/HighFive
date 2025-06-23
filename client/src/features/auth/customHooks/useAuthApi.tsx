import { useDispatch } from "react-redux";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { kakaoLoginApi, loginApi, nicknameInputApi, SignUpApi } from "../apis/AuthApi";
import { setToken } from "../slices/AuthSlice";
import { useAuthController } from "./useAuthController";
import type { LogInDto, MemberCreateDto, UserSignUpDto } from "../props/AuthProps";



export const useAuthApi = () => {
    const dispatch = useDispatch();

    const { setIsKakao, setKakaoEmail, setShowModalType } = useAuthController();

    const login = async (loginDto: LogInDto) => {
        try {
            const res = await loginApi(loginDto);
            console.log(res)
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const nicknameInput = async (nicknameInputDto: MemberCreateDto) => {
        try {
            await nicknameInputApi(nicknameInputDto);
        } catch (err) {
            printErrorInfo(err);
        }

    }

    const signUp = async (signUpDto: UserSignUpDto) => {
        try {
            const res = await SignUpApi(signUpDto);
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const kakaoLogin = () => {
        if (!window.Kakao.isInitialized()) return;

        window.Kakao.Auth.login({
            success: async (authObj: any) => {
                console.log('카카오 로그인 성공:', authObj);
                const kakaoAccessToken = authObj.access_token;

                try {
                    const res = await kakaoLoginApi({kakaoAccessToken});

                    if (res.status === 200 && res.data.accessToken) {
                        const { accessToken, refreshToken } = res.data;
                        dispatch(setToken({ accessToken, refreshToken }));
                    }
                }
                catch (err: any) {
                    if (err.response?.status === 404) {
                        const email = err.response.data;
                        console.log(email)
                        setKakaoEmail(email);
                        setIsKakao(true);
                        setShowModalType("signUp");

                    } else {
                        printErrorInfo(err);
                    }
                }


            },
            fail: function (err: any) {
                console.error('카카오 로그인 실패:', err);
            }
        });
    };


    return {
        login,
        nicknameInput,
        signUp,
        kakaoLogin,
    }
}