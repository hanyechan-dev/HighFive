import { useState } from "react";
import { useDispatch } from 'react-redux';
import { setToken } from '../auth/AuthSlice';



import { loginApi } from "./AuthApi.tsx";
import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";

const LoginModal = () => {
    const dispatch = useDispatch();
    const [showModal, setShowModal] = useState(true);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const onClose = () => { setShowModal(false) };

    const login = async (email: string, password: string) => {
        try {
            const res = await loginApi(email, password);
            const { accessToken, refreshToken } = res.data;
            console.log("리덕스");
            console.log(accessToken);
            dispatch(setToken({ accessToken, refreshToken }));
            console.log("팍 시발");
        } catch (err) {
            console.error('로그인 실패:', err);
        }
    };

    const kakaoLogin = () => {
        // 구현 필요
    };

    if (!showModal) return null

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

