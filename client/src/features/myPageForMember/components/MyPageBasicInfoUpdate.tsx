
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import { useState } from "react";
import type { MyPageUpdateDto } from "../props/myPageForMemberProps";
import DaumPostcode from 'react-daum-postcode';

interface MyPageBasicInfoUpdateProps {
    setShowModal: (showModal: boolean) => void;
    onUpdate: (myPageUpdateDto: MyPageUpdateDto) => void;
}

interface AddressData {
    roadAddress: string;
}

const MyPageBasicInfoUpdate = ({
    setShowModal,
    onUpdate

}: MyPageBasicInfoUpdateProps) => {

    const [showAddressModal, setShowAddressModal] = useState(false);

    const [myPageUpdateDto, setMyPageUpdateDto] = useState({
        phone: "",
        address: ""
    });

    const setPhone = (phone: string) => {
        setMyPageUpdateDto({
            ...myPageUpdateDto,
            phone
        })
    }

    const setAddress = (address: string) => {
        setMyPageUpdateDto({
            ...myPageUpdateDto,
            address
        })
    }


    const onClickUpdate = () => {

        if (myPageUpdateDto.phone.length !== 11) {
            alert("전화번호를 - 없이 11자리 입력해주세요.");
            return;
        }
        if (myPageUpdateDto.address === "") {
            alert("주소를 입력해주세요.");
            return;
        }

        onUpdate(myPageUpdateDto);
        setShowModal(false);
    } 

        const onClickAddressButton = () => {
        if (showAddressModal) {
            setShowAddressModal(false);
        } else {
            setShowAddressModal(true);
        }

    }

    const onCompleteAddress = (data: AddressData) => {
        setAddress(data.roadAddress);
        

    }

    const onCloseAddress = (state: string) => {
        if (state === 'FORCE_CLOSE') {
            setShowAddressModal(false);
        } else if (state === 'COMPLETE_CLOSE') {
            setShowAddressModal(false);
        }
    };

    return (
        <div>
            <Input label={"전화번호"} placeholder={"전화번호를 - 없이 입력해주세요."} size={"m"} disabled={false} type={"text"} value={myPageUpdateDto.phone} setValue={setPhone} />
            <Input label={'주소'} placeholder={'주소를 입력해주세요.'} size={'m'} disabled={true} type={'text'} value={myPageUpdateDto.address} setValue={() => { }} />
            <Button color={"white"} size={"l"} disabled={false} text={"주소검색"} onClick={onClickAddressButton} type={"button"} />
            {showAddressModal && <DaumPostcode className="mb-6" style={{ height: "445px" }} onComplete={onCompleteAddress} onClose={onCloseAddress} />}
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
};

export default MyPageBasicInfoUpdate;