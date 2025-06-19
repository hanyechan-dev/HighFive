import { useEffect, useState } from "react";
import { useDispatch } from 'react-redux';
import { setToken } from '../auth/AuthSlice';



import { SignUpApi } from "./AuthApi.tsx";
import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";
import RadioButton from "../../common/components/button/RadioButton.tsx";
import Select from "../../common/components/input/Select.tsx";
import { genderTypeEnum, userTypeEnum } from "../../common/enum/Enum.tsx";
import NicknameInputModal from "../member/NicknameInputModal.tsx";
import CompanyInfoInputModal from "../../company/companyInfo/CompanyInfoInputModal.tsx";

interface SignUpModalProps {
    kakaoEmail?: string;
    onClose: () => void;
}

const SignUpModal = ({ kakaoEmail, onClose }: SignUpModalProps) => {


    const dispatch = useDispatch();

    const [isKakao, setIsKakao] = useState(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');

    const [checkedUserType, setCheckedUserType] = useState(userTypeEnum[0].value)
    const [selectedGenderType, setSelectedGenderType] = useState(genderTypeEnum[0].value)

    useEffect(() => {
        if (kakaoEmail) {
            setEmail(kakaoEmail);
            setIsKakao(true);
        }
    }, [kakaoEmail]);

    const [showNicknameModal, setShowNicknameModal] = useState(false);
    const [showCompanyInfoModal, setShowCompanyInfoModal] = useState(false);

    useEffect(() => {
        if (showNicknameModal && showCompanyInfoModal) {
            onClose();
        }
    }, [showNicknameModal, showCompanyInfoModal]);

    const signUp = async (
        email: string,
        password: string,
        name: string,
        birthDate: string,
        genderType: string,
        phone: string,
        address: string,
        type: string) => {
        try {
            const res = await SignUpApi(email, password, name, birthDate, genderType, phone, address, type);
            const { accessToken, refreshToken } = res.data;
            dispatch(setToken({ accessToken, refreshToken }));

            if (type == '일반회원') {
                setShowNicknameModal(true);
                onClose();
            }

            else if (type == '기업회원') {
                setShowCompanyInfoModal(true);
                onClose();
            }


        } catch (err) {
            console.error('회원가입 실패:', err);
        }
    };

    return (
        <>
            <CommonModal size="m" onClose={onClose} >
                <ModalTitle title={'회원가입'} />
                <RadioButton name={"회원유형"} textList={userTypeEnum} checkedText={checkedUserType} setCheckedText={setCheckedUserType} />
                <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={isKakao ? true : false} type={'email'} value={email} setValue={setEmail} />
                <Input label={'비밀번호'} placeholder={'비밀번호를 입력해주세요.'} size={'m'} disabled={false} type={'password'} value={password} setValue={setPassword} />
                <Input label={'이름'} placeholder={'이름을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={name} setValue={setName} />
                <Input label={'생년월일'} placeholder={'생년월일을 입력해주세요.'} size={'m'} disabled={false} type={'date'} value={birthDate} setValue={setBirthDate} />
                <Select label={"성별"} options={genderTypeEnum} size={"m"} disabled={false} value={selectedGenderType} setValue={setSelectedGenderType} />
                <Input label={'전화번호'} placeholder={'전화번호를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={phone} setValue={setPhone} />
                <Input label={'주소'} placeholder={'주소를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={address} setValue={setAddress} />
                <Button color={"theme"} size={"l"} disabled={false} text={"회원가입"} onClick={() => signUp(email, password, name, birthDate, selectedGenderType, phone, address, checkedUserType)} type={"button"} />
            </CommonModal>

            {showNicknameModal && <NicknameInputModal />}
            {showCompanyInfoModal && <CompanyInfoInputModal />}
        </>
    );

};

export default SignUpModal;

