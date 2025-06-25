import { useState } from "react";
import ModalTitle from "../../../common/components/title/ModalTitle";
import Input from "../../../common/components/input/Input";
import Button from "../../../common/components/button/Button";
import type { MemberCreateDto } from "../props/AuthProps";


interface NicknameInputModalProps {
    nicknameInput: (nicknameInputDto: MemberCreateDto) => Promise<boolean>;
    onClose: () => void;
}


const NicknameInputModal = ({ nicknameInput, onClose }: NicknameInputModalProps) => {
    const [memberCreateDto, setMemberCreateDto] = useState<MemberCreateDto>({
        nickname: ''
    });

    const setNickname = (nickname: string) => {
        setMemberCreateDto({
            ...memberCreateDto,
            nickname
        })
    }

    const onClickNicknameInputButton = async () => {
        const result = await nicknameInput(memberCreateDto);

        if (result) {
            onClose();
        }

    }





    return (
        <>
            <ModalTitle title={'닉네임 입력'} />
            <Input label={'닉네임'} placeholder={'닉네임을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={memberCreateDto.nickname} setValue={setNickname} />
            <Button color={"theme"} size={"l"} disabled={false} text={"저장"} onClick={() => onClickNicknameInputButton()} type={"button"} />
        </>

    );

};

export default NicknameInputModal;

