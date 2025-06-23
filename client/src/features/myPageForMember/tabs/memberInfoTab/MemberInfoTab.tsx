
import { useEffect } from "react";
import { PageBox } from "../../../../common/components/box/Box";
import Button from "../../../../common/components/button/Button";
import Input from "../../../../common/components/input/Input";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import { useMemberInfoTabController } from "../../customHooks/MemberInfoTab/useMemberInfoTabController";
import MemberInfoUpdateModal from "../../modals/MemberInfoUpdateModal";
import { useMemberInfoTabApi } from "../../customHooks/MemberInfoTab/useMemberInfoTabApi";

const MemberInfoTab = () => {

    const {
        showModal,
        memberMyPageResponseDto,
        setShowModal,
    } = useMemberInfoTabController();

    const { readMyPage, deactivateAccount } = useMemberInfoTabApi();

    useEffect(() => {
        readMyPage();
    }, []);

    if (!memberMyPageResponseDto) return null;

    const onClickDeactivateAccountButton = () => {
        deactivateAccount();
    }

    return (
        <>
            <PageBox >
                <div className="flex gap-3">
                    <ModalTitle title={"회원정보"} />
                    <div className="flex gap-[803px] ">
                        <Button color={"action"} size={"s"} disabled={false} text={"회원탈퇴"} type={"button"} onClick={() => { onClickDeactivateAccountButton() }} />
                    </div>
                </div>
                <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
                    회원님의 기본 정보를 확인하고 관리할 수 있습니다.
                </div>
                <div className="border-b w-[1120px] ml-6 mb-6"></div>
                <div className="flex">
                    <Input label={"이메일"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.email ?? ""} setValue={() => { }} />
                    <Input label={"닉네임"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.memberResponseDto?.nickname ?? ""} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label={"이름"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.name ?? ""} setValue={() => { }} />
                    <Input label={"전화번호"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.phone ?? ""} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label={"생년월일"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.birthDate ?? ""} setValue={() => { }} />
                    <Input label={"성별"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.genderType ?? ""} setValue={() => { }} />
                </div>
                <Input label={"주소"} placeholder={""} size={"pbl"} disabled={true} type={"text"} value={memberMyPageResponseDto?.myPageResponseDto?.address ?? ""} setValue={() => { }} />
                <div className="flex justify-end mr-6">
                    <Button color={"theme"} size={"m"} disabled={false} text={"회원정보 수정"} type={"button"} onClick={() => { setShowModal(true) }} />
                </div>
            </PageBox>

            {showModal && <MemberInfoUpdateModal />}
        </>
    )

}

export default MemberInfoTab;

