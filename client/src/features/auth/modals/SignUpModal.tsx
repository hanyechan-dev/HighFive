
import { useState } from "react";
import RadioButton from "../../../common/components/button/RadioButton";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { genderTypeEnum, userTypeEnum } from "../../../common/enum/Enum";
import Select from "../../../common/components/input/Select";
import Button from "../../../common/components/button/Button";
import type { UserSignUpDto } from "../props/AuthProps";
import DaumPostcode from 'react-daum-postcode';

interface AddressData {
    roadAddress: string;
}



interface SignUpModalProps {
    isKakao: boolean;
    kakaoEmail: string;
    setShowModalType: (showModalType: string) => void;
    signUp: (signUpDto: UserSignUpDto) => void;
    onClose: () => void;
}

const SignUpModal = ({ isKakao, kakaoEmail, setShowModalType, signUp, onClose }: SignUpModalProps) => {

    const [showAddressModal, setShowAddressModal] = useState(false);

    const [signUpDto, setSignUpDto] = useState<UserSignUpDto>(() => {
        if (isKakao) {
            return {
                email: kakaoEmail,
                password: '',
                name: '',
                birthDate: '',
                genderType: '',
                phone: '',
                address: '',
                userType: '일반회원',
            };
        } else {
            return {
                email: '',
                password: '',
                name: '',
                birthDate: '',
                genderType: '',
                phone: '',
                address: '',
                userType: '일반회원',
            };
        }
    });

    const setSignUpField = (field: keyof UserSignUpDto) => (value: string) => {
        setSignUpDto(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const onClickSignUpButton = () => {
        signUp(signUpDto);
        if (signUpDto.userType === "기업회원") {
            setShowModalType("companyInfoInput");
        }
        else if (signUpDto.userType === "일반회원") {
            setShowModalType("nicknameInput");
        }
        else {
            onClose();
        }
    }

    const onClickAddressButton = () => {
        if(showAddressModal){
            setShowAddressModal(false);
        }else{
            setShowAddressModal(true);
        }

    }

    const onCompleteAddress = (data : AddressData) => {
        setSignUpField("address")(data.roadAddress);

    }

    const onCloseAddress = (state : string) => {
        if (state === 'FORCE_CLOSE') {
            setShowAddressModal(false);
        } else if (state === 'COMPLETE_CLOSE') {
            setShowAddressModal(false);
        }
    };

    return (
        <>
            <ModalTitle title={'회원가입'} />
            <RadioButton name={"회원유형"} textList={userTypeEnum} checkedText={signUpDto.userType} setCheckedText={setSignUpField("userType")} />
            <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={isKakao ? true : false} type={'email'} value={signUpDto.email} setValue={setSignUpField("email")} />
            <Input label={'비밀번호'} placeholder={'비밀번호를 입력해주세요.'} size={'m'} disabled={false} type={'password'} value={signUpDto.password} setValue={setSignUpField("password")} />
            <Input label={'이름'} placeholder={'이름을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={signUpDto.name} setValue={setSignUpField("name")} />
            <Input label={'생년월일'} placeholder={'생년월일을 입력해주세요.'} size={'m'} disabled={false} type={'date'} value={signUpDto.birthDate} setValue={setSignUpField("birthDate")} />
            <Select label={"성별"} options={genderTypeEnum} size={"m"} disabled={false} value={signUpDto.genderType} setValue={setSignUpField("genderType")} />
            <Input label={'전화번호'} placeholder={'전화번호를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={signUpDto.phone} setValue={setSignUpField("phone")} />
            <Input label={'주소'} placeholder={'주소를 입력해주세요.'} size={'m'} disabled={true} type={'text'} value={signUpDto.address} setValue={() => { }} />
            <Button color={"white"} size={"l"} disabled={false} text={"주소검색"} onClick={onClickAddressButton} type={"button"} />
            {showAddressModal && <DaumPostcode className="mb-6" style={{height: "445px"}}  onComplete={onCompleteAddress} onClose={onCloseAddress}/>}
            <Button color={"theme"} size={"l"} disabled={false} text={"회원가입"} onClick={onClickSignUpButton} type={"button"} />




        </>
    );

};

export default SignUpModal;

