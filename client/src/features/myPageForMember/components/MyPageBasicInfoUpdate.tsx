
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import { useState } from "react";
import type { MyPageUpdateDto } from "../props/myPageForMemberProps";

interface MyPageBasicInfoUpdateProps {
    setShowModal: (showModal: boolean) => void;
    onUpdate: (myPageUpdateDto: MyPageUpdateDto) => void;
}

const MyPageBasicInfoUpdate = ({
    setShowModal,
    onUpdate

}: MyPageBasicInfoUpdateProps) => {

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

    return (
        <div>
            <Input label={"전화번호"} placeholder={"전화번호를 - 없이 입력해주세요."} size={"m"} disabled={false} type={"text"} value={myPageUpdateDto.phone} setValue={setPhone} />
            <Input label={"주소"} placeholder={"주소를 입력해주세요."} size={"m"} disabled={false} type={"text"} value={myPageUpdateDto.address} setValue={setAddress} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
};

export default MyPageBasicInfoUpdate;