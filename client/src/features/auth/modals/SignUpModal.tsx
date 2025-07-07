
import { useState } from "react";
import RadioButton from "../../../common/components/button/RadioButton";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { genderTypeEnum, userTypeEnum } from "../../../common/enum/Enum";
import Select from "../../../common/components/input/Select";
import Button from "../../../common/components/button/Button";
import type { KakaoUserSignUpDto, UserSignUpDto } from "../props/AuthProps";
import DaumPostcode from 'react-daum-postcode';

interface AddressData {
    roadAddress: string;
}



interface SignUpModalProps {
    isKakao: boolean;
    kakaoEmail: string;
    setShowModalType: (showModalType: string) => void;
    signUp: (signUpDto: UserSignUpDto) => Promise<boolean>;
    kakaoSignUp: (kakaoSignUpDto: KakaoUserSignUpDto) => Promise<boolean>;
    onClose: () => void;
}

const SignUpModal = ({ isKakao, kakaoEmail, setShowModalType, signUp, kakaoSignUp, onClose }: SignUpModalProps) => {

    const [showAddressModal, setShowAddressModal] = useState(false);

    const [signUpDto, setSignUpDto] = useState<UserSignUpDto>({
        email: '',
        password: '',
        name: '',
        birthDate: '',
        genderType: '',
        phone: '',
        address: '',
        userType: '일반회원',
    });

    const [kakaoSignUpDto, setKakaoSignUpDto] = useState<KakaoUserSignUpDto>({
        email: kakaoEmail,
        name: '',
        birthDate: '',
        genderType: '',
        phone: '',
        address: '',
        userType: '일반회원',
    })


    const setSignUpField = (field: keyof UserSignUpDto) => (value: string) => {
        setSignUpDto(prev => ({
            ...prev,
            [field]: value
        }));
    };

    const setKakaoSignUpField = (field: keyof KakaoUserSignUpDto) => (value: string) => {
        setKakaoSignUpDto(prev => ({
            ...prev,
            [field]: value
        }));
    };



    const onClickSignUpButton = async () => {

        let result;

        if (isKakao) {
            result = await kakaoSignUp(kakaoSignUpDto);
        }
        else {
            result = await signUp(signUpDto);
        }



        if (result) {
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

    }

    const onClickAddressButton = () => {
        if (showAddressModal) {
            setShowAddressModal(false);
        } else {
            setShowAddressModal(true);
        }

    }

    const onCompleteAddress = (data: AddressData) => {
        isKakao ? setKakaoSignUpField("address")(data.roadAddress) : setSignUpField("address")(data.roadAddress);
        

    }

    const onCloseAddress = (state: string) => {
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
            <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={isKakao ? true : false} type={'email'} value={isKakao? kakaoSignUpDto.email : signUpDto.email} setValue={isKakao ? setKakaoSignUpField("email") : setSignUpField("email")} />
            <Input label={'비밀번호'} placeholder={isKakao ? '소셜 로그인은 비밀번호 입력 X' : '비밀번호를 입력해주세요.'} size={'m'} disabled={isKakao ? true : false} type={'password'} value={isKakao? '' : signUpDto.password} setValue={isKakao ? ()=>{} : setSignUpField("password")} />
            <Input label={'이름'} placeholder={'이름을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={isKakao? kakaoSignUpDto.name : signUpDto.name} setValue={isKakao ? setKakaoSignUpField("name") : setSignUpField("name")} />
            <Input label={'생년월일'} placeholder={'생년월일을 입력해주세요.'} size={'m'} disabled={false} type={'date'} value={isKakao? kakaoSignUpDto.birthDate : signUpDto.birthDate} setValue={isKakao ? setKakaoSignUpField("birthDate") : setSignUpField("birthDate")} />
            <Select label={"성별"} options={genderTypeEnum} size={"m"} disabled={false} value={isKakao? kakaoSignUpDto.genderType :  signUpDto.genderType} setValue={isKakao ? setKakaoSignUpField("genderType") : setSignUpField("genderType")} />
            <Input label={'전화번호'} placeholder={'전화번호를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={isKakao? kakaoSignUpDto.phone : signUpDto.phone} setValue={isKakao ? setKakaoSignUpField("phone") : setSignUpField("phone")} />
            <Input label={'주소'} placeholder={'주소를 입력해주세요.'} size={'m'} disabled={true} type={'text'} value={isKakao? kakaoSignUpDto.address : signUpDto.address} setValue={() => { }} />
            <Button color={"white"} size={"l"} disabled={false} text={"주소검색"} onClick={onClickAddressButton} type={"button"} />
            {showAddressModal && <DaumPostcode className="mb-6" style={{ height: "445px" }} onComplete={onCompleteAddress} onClose={onCloseAddress} />}
            <Button color={"theme"} size={"l"} disabled={false} text={"회원가입"} onClick={onClickSignUpButton} type={"button"} />




        </>
    );

};

export default SignUpModal;

