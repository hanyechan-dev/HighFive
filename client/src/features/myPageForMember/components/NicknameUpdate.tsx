import { useState } from "react";
import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";

const NicknameUpdate = () => {

    const {
        setMemberUpdateDto,
        setShowMemberInfoUpdateModal,
    } = useMyPageForMemberPageController();

    const [nickname, setNickname] = useState("");

    const onClickUpdate = () => {
        if (nickname === "") {
            alert("닉네임을 입력해주세요.");
            return;
        }
        setMemberUpdateDto({
            nickname
        })
        setShowMemberInfoUpdateModal(false);
    } 



    return (
        <div>
            <Input label={"닉네임"} placeholder={"닉네임을 입력해주세요."} size={"m"} disabled={false} type={"text"} value={nickname} setValue={setNickname} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
};

export default NicknameUpdate;
