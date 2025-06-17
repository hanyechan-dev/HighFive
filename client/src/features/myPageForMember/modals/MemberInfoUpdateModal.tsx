import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal"
import RadioButton from "../../../common/components/button/RadioButton";
import MyPageBasicInfoUpdate from "../components/MyPageBasicInfoUpdate";
import NicknameUpdate from "../components/NicknameUpdate";
import PasswordUpdate from "../components/PasswordUpdate";
import { useMemberInfoTabController } from "../customHooks/MemberInfoTab/useMemberInfoTabController";

const memberInfoUpdateTextList = [
    { label: "기본정보", value: "기본정보" },
    { label: "닉네임", value: "닉네임" },
    { label: "비밀번호", value: "비밀번호" }
]



const MemberInfoUpdateModal = () => {


    const {
        showMemberInfoUpdateModalTab,
        setShowMemberInfoUpdateModalTab,
        setShowMemberInfoUpdateModal,
    } = useMemberInfoTabController();

    const onClose = () => {
        setShowMemberInfoUpdateModal(false);
    }





    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={"회원정보 수정"} />
            <RadioButton name={""} textList={memberInfoUpdateTextList} checkedText={showMemberInfoUpdateModalTab} setCheckedText={setShowMemberInfoUpdateModalTab} />
            {showMemberInfoUpdateModalTab === "기본정보" && <MyPageBasicInfoUpdate />}
            {showMemberInfoUpdateModalTab === "닉네임" && <NicknameUpdate />}
            {showMemberInfoUpdateModalTab === "비밀번호" && <PasswordUpdate />}

        </CommonModal>
    )
}

export default MemberInfoUpdateModal
