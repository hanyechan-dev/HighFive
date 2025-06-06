import { useState } from "react";
import { useDispatch } from 'react-redux';
import { setToken } from '../auth/AuthSlice';



import { SignUpApi } from "./AuthApi.tsx";
import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";
import RadioButton from "../../common/components/button/RadioButton.tsx";
import Select from "../../common/components/input/Select.tsx";

const SignUpModal = () => {

    const onClose = () => { setShowModal(false) };
    
    const dispatch = useDispatch();
    const [showModal, setShowModal] = useState(true);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    

    const userType = ['일반회원', '기업회원', '컨설턴트회원'];
    const [checkedUserType, setCheckedUserType] = useState(userType[0])

    const genderType = ['남자', '여자'];
    const [selectedGenderType, setSelectedGenderType] = useState(genderType[0])

    const signUp = async (
        email: string,
        password: string,
        name: string,
        birthDate: string,
        genderType: string,
        phone : string,
        address : string,
        type : string) => {
        try {
            const res = await SignUpApi(email, password, name, birthDate, genderType, phone, address, type);
            const { accessToken, refreshToken } = res.data;
            console.log(accessToken);
            dispatch(setToken({ accessToken, refreshToken }));
        } catch (err) {
            console.error('회원가입 실패:', err);
        }
    };

    if (!showModal) return null

    return (

        <CommonModal size="m" onClose={onClose} >
            <ModalTitle title={'회원가입'} />
            <RadioButton name={"회원유형"} textList={userType} checkedText={checkedUserType} setCheckedText={setCheckedUserType}/>
            <Input label={'이메일'} placeholder={'이메일 주소를 입력해주세요.'} size={'m'} disabled={false} type={'email'} value={email} setValue={setEmail} />
            <Input label={'비밀번호'} placeholder={'비밀번호를 입력해주세요.'} size={'m'} disabled={false} type={'password'} value={password} setValue={setPassword} />
            <Input label={'이름'} placeholder={'이름을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={name} setValue={setName} />
            <Input label={'생년월일'} placeholder={'생년월일을 입력해주세요.'} size={'m'} disabled={false} type={'date'} value={birthDate} setValue={setBirthDate} />
            <Select label={"성별"} options={genderType} size={"m"} disabled={false} value={selectedGenderType} setValue={setSelectedGenderType}  />
            <Input label={'전화번호'} placeholder={'전화번호를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={phone} setValue={setPhone} />
            <Input label={'주소'} placeholder={'주소를 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={address} setValue={setAddress} />
            <Button color={"theme"} size={"l"} disabled={false} text={"회원가입"} onClick={() => signUp(email, password, name, birthDate, selectedGenderType, phone, address, checkedUserType)} type={"button"} />
        </CommonModal>

    );

};

export default SignUpModal;

