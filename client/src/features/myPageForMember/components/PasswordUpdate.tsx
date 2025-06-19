import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import { useState } from "react";
import type { PasswordUpdateDto } from "../props/myPageForMemberProps";

interface PasswordUpdateProps {
    setShowModal: (showModal: boolean) => void;
    onUpdate: (passwordUpdateDto: PasswordUpdateDto) => void;
}

const PasswordUpdate = ({
    setShowModal,
    onUpdate
}: PasswordUpdateProps) => {

    const [passwordUpdateDto, setPasswordUpdateDto] = useState<PasswordUpdateDto>({
        password: "",
        newPassword: "",
        newPasswordCheck: ""
    });

    const setPassword = (password: string) => {
        setPasswordUpdateDto({
            ...passwordUpdateDto,
            password
        })
    }

    const setNewPassword = (newPassword: string) => {
        setPasswordUpdateDto({
            ...passwordUpdateDto,
            newPassword
        })
    }

    const setNewPasswordCheck = (newPasswordCheck: string) => {
        setPasswordUpdateDto({
            ...passwordUpdateDto,
            newPasswordCheck
        })
    }


    const onClickUpdate = () => {
        if (passwordUpdateDto.password === "") {
            alert("비밀번호를 입력해주세요.");
            return;
        }
        if (passwordUpdateDto.newPassword === "") {
            alert("새 비밀번호를 입력해주세요.");
            return;
        }
        if (passwordUpdateDto.newPasswordCheck === "") {
            alert("비밀번호 확인을 입력해주세요.");
            return;
        }

        if (passwordUpdateDto.newPassword !== passwordUpdateDto.newPasswordCheck) {
            alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        onUpdate(passwordUpdateDto)
        setShowModal(false);
    }


    return (
        <div>
            <Input label={"비밀번호"} placeholder={"비밀번호를 입력해주세요."} size={"m"} disabled={false} type={"password"} value={passwordUpdateDto.password} setValue={setPassword} />
            <Input label={"새 비밀번호"} placeholder={"새 비밀번호를 입력해주세요."} size={"m"} disabled={false} type={"password"} value={passwordUpdateDto.newPassword} setValue={setNewPassword} />
            <Input label={"비밀번호 확인"} placeholder={"비밀번호를 다시 입력해주세요."} size={"m"} disabled={false} type={"password"} value={passwordUpdateDto.newPasswordCheck} setValue={setNewPasswordCheck} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
}

export default PasswordUpdate
