import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import { useState } from "react";

const PasswordUpdate = () => {

    const {
        setPasswordUpdateDto,
        setShowMemberInfoUpdateModal,
    } = useMyPageForMemberPageController();

    const [password, setPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [newPasswordCheck, setNewPasswordCheck] = useState("");

    const onClickUpdate = () => {
        if (password === "") {
            alert("비밀번호를 입력해주세요.");
            return;
        }
        if (newPassword === "") {
            alert("새 비밀번호를 입력해주세요.");
            return;
        }
        if (newPasswordCheck === "") {
            alert("비밀번호 확인을 입력해주세요.");
            return;
        }

        if (newPassword !== newPasswordCheck) {
            alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        setPasswordUpdateDto({
            password,
            newPassword,
            newPasswordCheck
        })
        setShowMemberInfoUpdateModal(false);
    }


    return (
        <div>
            <Input label={"비밀번호"} placeholder={"비밀번호를 입력해주세요."} size={"m"} disabled={false} type={"password"} value={password} setValue={setPassword} />
            <Input label={"새 비밀번호"} placeholder={"새 비밀번호를 입력해주세요."} size={"m"} disabled={false} type={"password"} value={newPassword} setValue={setNewPassword} />
            <Input label={"비밀번호 확인"} placeholder={"비밀번호를 다시 입력해주세요."} size={"m"} disabled={false} type={"password"} value={newPasswordCheck} setValue={setNewPasswordCheck} />
            <Button color={"theme"} size={"l"} disabled={false} text={"수정"} onClick={onClickUpdate} type={"button"} />
        </div>
    )
}

export default PasswordUpdate
