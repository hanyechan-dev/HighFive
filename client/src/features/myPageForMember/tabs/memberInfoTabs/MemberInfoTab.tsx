
import { useEffect } from "react";
import Badge from "../../../../common/components/badge/Badge";
import { PageBox } from "../../../../common/components/box/Box";
import Button from "../../../../common/components/button/Button";
import Input from "../../../../common/components/input/Input";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import CompanyTypeIcon from "../../../../common/icons/CompanyTypeIcon";
import { useMemberInfoTabController } from "../../customHooks/MemberInfoTab/useMemberInfoTabController";
import MemberInfoUpdateModal from "../../modals/MemberInfoUpdateModal";
import { readMyPageApi } from "../../apis/MyPageForMemberApi";
import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi";

const MemberInfoTab = () => {

    const {
        showMemberInfoUpdateModal,
        memberMyPageResponseDto,
        setShowMemberInfoUpdateModal,
    } = useMemberInfoTabController();

    const {
        memberResponseDto: {
            nickname
        },
        myPageResponseDto: {
            email,
            name,
            birthDate,
            genderType,
            phone,
            address,
            type,
        }
    } = memberMyPageResponseDto;

    return (
        <>
            <PageBox >
                <div className="flex gap-3">
                    <ModalTitle title={"회원정보"} />
                    <div className="mt-1">
                        <Badge icon={<CompanyTypeIcon />} text={type} />
                    </div>
                </div>
                <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
                    회원님의 기본 정보를 확인하고 관리할 수 있습니다.
                </div>
                <div className="border-b w-[1120px] ml-6 mb-6"></div>
                <div className="flex">
                    <Input label={"이메일"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={email} setValue={() => { }} />
                    <Input label={"닉네임"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={nickname} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label={"이름"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={name} setValue={() => { }} />
                    <Input label={"전화번호"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={phone} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label={"생년월일"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={birthDate} setValue={() => { }} />
                    <Input label={"성별"} placeholder={""} size={"pbm"} disabled={true} type={"text"} value={genderType} setValue={() => { }} />
                </div>
                <Input label={"주소"} placeholder={""} size={"pbl"} disabled={true} type={"text"} value={address} setValue={() => { }} />
                <div className="flex justify-end mr-6">
                    <Button color={"theme"} size={"m"} disabled={false} text={"회원정보 수정"} type={"button"} onClick={() => {setShowMemberInfoUpdateModal(true)}} />
                </div>
            </PageBox>

            {showMemberInfoUpdateModal && <MemberInfoUpdateModal />}
        </>
    )

}

export default MemberInfoTab;