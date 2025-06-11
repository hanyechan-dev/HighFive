import { useState } from "react";

import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";
import { nicknameInputApi } from "./MemberApi.tsx";

const NicknameInputModal = () => {
    const [showModal, setShowModal] = useState(true);
    const [nickname, setNickname] = useState('');
    const onClose = () => { setShowModal(false) };

    const nicknameInput = async (nickname: string) => {
        try {
            await nicknameInputApi(nickname);

            onClose();

        } catch (err) {
            console.error('닉네임 입력 실패:', err);
        }
    };

    if (!showModal) return null

    return (

        <CommonModal size="m" onClose={()=>{}} >
            <ModalTitle title={'닉네임 입력'} />
            <Input label={'닉네임'} placeholder={'닉네임을 입력해주세요.'} size={'m'} disabled={false} type={'text'} value={nickname} setValue={setNickname} />
            <Button color={"theme"} size={"l"} disabled={false} text={"저장"} onClick={() => nicknameInput(nickname)} type={"button"} />
        </CommonModal>

    );

};

export default NicknameInputModal;

