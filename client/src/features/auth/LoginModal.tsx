import { useState } from "react";
import { useDispatch } from 'react-redux';
import { setToken } from '../auth/AuthSlice';



import { kakaoLoginApi, loginApi } from "./AuthApi.tsx";
import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";

interface LoginModalProps {
    setKakaoEmail: (email: string) => void;
    onClose: () => void;
    onSwitchToSignUp: () => void;
}

const LoginModal = ({ setKakaoEmail,onClose,onSwitchToSignUp }: LoginModalProps) => {
    console.log("setKakaoEmail 타입:", typeof setKakaoEmail);


    const dispatch = useDispatch();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const login = async (email: string, password: string) => {
        try {
            const res = await loginApi(email, password);
            const { accessToken, refreshToken } = res.data;

            dispatch(setToken({ accessToken, refreshToken }));

            onClose();

        } catch (err) {
            console.error('로그인 실패:', err);
        }
    };

    const kakaoLogin = () => {
        if (!window.Kakao.isInitialized()) return;

        window.Kakao.Auth.login({
            success: async (authObj: any) => {
                console.log('카카오 로그인 성공:', authObj);
                const kakaoAccessToken = authObj.access_token;

                try {
                    const res = await kakaoLoginApi(kakaoAccessToken);

                    if (res.status === 200 && res.data.accessToken) {
                        const { accessToken, refreshToken } = res.data;
                        dispatch(setToken({ accessToken, refreshToken }));
                        onClose();
                    }
                }
                catch (err: any) {
                    if (err.response?.status === 404) {
                        const email = err.response.data;
                        setKakaoEmail(email);
                        onClose();
                        onSwitchToSignUp();

                    } else {
                        console.error("기타 백엔드 오류:", err.response?.status, err.response?.data);
                    }
                }


            },
            fail: function (err: any) {
                console.error('카카오 로그인 실패:', err);
            }
        });
    };

    return (

        <CommonModal size="m" onClose={onClose} >
            <ModalTitle title={'로그인'} />
            <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={false} type={'email'} value={email} setValue={setEmail} />
            <Input label={'비밀번호'} placeholder={'비밀번호를 입력해주세요.'} size={'m'} disabled={false} type={'password'} value={password} setValue={setPassword} />
            <Button color={"theme"} size={"l"} disabled={false} text={"로그인"} onClick={() => login(email, password)} type={"button"} />
            <Button color={"kakao"} size={"l"} disabled={false} text={"카카오톡 로그인"} onClick={kakaoLogin} type={"button"} />
        </CommonModal>

    );

};

export default LoginModal;

