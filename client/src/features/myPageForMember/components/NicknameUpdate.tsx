import { useState } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import type { MemberUpdateDto } from "../props/myPageForMemberProps";

interface NicknameUpdateProps {
    setShowModal: (showModal: boolean) => void;
    onUpdate: (memberUpdateDto: MemberUpdateDto) => void;
}

const NicknameUpdate = ({
    setShowModal,
    onUpdate
}: NicknameUpdateProps) => {

    const [memberUpdateDto, setMemberUpdateDto] = useState<MemberUpdateDto>({
        nickname: ""
    });

    const setNickname = (nickname: string) => {
        setMemberUpdateDto({
            ...memberUpdateDto,
            nickname
        })
    }

    const onClickUpdate = () => {
        if (memberUpdateDto.nickname === "") {
            alert("닉네임을 입력해주세요.");
            return;
        }
        onUpdate(memberUpdateDto)
        setShowModal(false);
    } 



    return (
        <div>
            <Input label={"닉네임"} placeholder={"닉네임을 입력해주세요."} size={"m"} disabled={false} type={"text"} value={memberUpdateDto.nickname} setValue={setNickname} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
};

export default NicknameUpdate;
