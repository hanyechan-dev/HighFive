import { useState } from "react";
import ModalTitle from "../../../common/components/title/ModalTitle";
import Input from "../../../common/components/input/Input";
import Button from "../../../common/components/button/Button";
import type { LogInDto } from "../props/AuthProps";



interface LoginModalProps {
    setShowModalType: (showModalType: string) => void;
    login: (loginDto: LogInDto) => void;
    kakaoLogin: () => void;
    onClose: () => void;
}

const LoginModal = ({ setShowModalType, login, kakaoLogin, onClose }: LoginModalProps) => {

    const [loginDto, setLoginDto] = useState<LogInDto>({
        email: '',
        password: ''
    });


    const setEmail = (email: string) => {
        setLoginDto({
            ...loginDto,
            email
        })
    }

    const setPassword = (password: string) => {
        setLoginDto({
            ...loginDto,
            password
        })
    }

    const onClickloginButton = () => {
        login(loginDto);
        onClose()
    }

    const onClickKakaoLoginButton = () => {
        kakaoLogin();
    }


    return(
        <>
            <ModalTitle title={'로그인'} />
            <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={false} type={'email'} value={loginDto.email} setValue={setEmail} />
            <Input label={'비밀번호'} placeholder={'비밀번호를 입력해주세요.'} size={'m'} disabled={false} type={'password'} value={loginDto.password} setValue={setPassword} />
            <Button color={"theme"} size={"l"} disabled={false} text={"로그인"} onClick={() => onClickloginButton()} type={"button"} />
            <Button color={"kakao"} size={"l"} disabled={false} text={"카카오톡 로그인"} onClick={() => onClickKakaoLoginButton()} type={"button"} />
            <div className="flex justify-center font-roboto mb-6 gap-2">
                계정이 없으신가요?<span onClick={() => setShowModalType("signUp")} className="text-theme font-semibold cursor-pointer">회원가입</span>
            </div>
        </>

    );

};

export default LoginModal;