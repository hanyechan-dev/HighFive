
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import { useState } from "react";
import { useMemberInfoTabController } from "../customHooks/MemberInfoTab/useMemberInfoTabController";

const MyPageBasicInfoUpdate = () => {

    const {
        setMyPageUpdateDto,
        setShowMemberInfoUpdateModal,
    }= useMemberInfoTabController();




    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");


    const onClickUpdate = () => {

        if (phone.length !== 11) {
            alert("전화번호를 - 없이 11자리 입력해주세요.");
            return;
        }
        if (address === "") {
            alert("주소를 입력해주세요.");
            return;
        }

        setMyPageUpdateDto({
            phone,
            address
        })
        setShowMemberInfoUpdateModal(false);
    } 

    return (
        <div>
            <Input label={"전화번호"} placeholder={"전화번호를 - 없이 입력해주세요."} size={"m"} disabled={false} type={"text"} value={phone} setValue={setPhone} />
            <Input label={"주소"} placeholder={"주소를 입력해주세요."} size={"m"} disabled={false} type={"text"} value={address} setValue={setAddress} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
};

export default MyPageBasicInfoUpdate;